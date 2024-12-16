package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.Archive;
import net.botwithus.rs3.cache.ArchiveFile;
import net.botwithus.rs3.cache.Filesystem;
import net.botwithus.rs3.cache.ReferenceTable;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.vars.VarLoader;
import net.botwithus.rs3.cache.assets.vars.VarType;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class VarProvider implements ConfigProvider<VarType> {

    private static final Logger log = Logger.getLogger(VarProvider.class.getName());

    private final Map<Integer, VarType> vars;

    private final Filesystem fs;

    private final VarLoader loader;

    public VarProvider(Filesystem fs) {
        this.fs = fs;
        this.loader = new VarLoader();
        this.vars = new HashMap<>();
    }

    @Override
    public String name() {
        return "var_types";
    }

    @Override
    public VarType provide(int id) {
        try {
            if (vars.containsKey(id)) {
                return vars.get(id);
            }
            ReferenceTable table = fs.getReferenceTable(2, false);
            if (table == null) {
                return null;
            }
            Archive archive = table.loadArchive(60);
            if (archive == null) {
                return null;
            }
            ArchiveFile file = archive.files.get(id);
            if (file == null) {
                return null;
            }
            VarType var = new VarType(id);
            loader.load(var, ByteBuffer.wrap(file.getData()));
            vars.put(id, var);
            return var;
        } catch (Exception e) {
            log.log(java.util.logging.Level.SEVERE, "Failed to load var type", e);
        }
        return null;
    }

    @Override
    public int capacity() {
        try {
            ReferenceTable table = fs.getReferenceTable(2, false);
            if (table == null) {
                return 0;
            }
            Archive archive = table.loadArchive(60);
            if (archive == null) {
                return 0;
            }
            return archive.files.size();
        } catch (Exception e) {
            log.throwing(VarProvider.class.getName(), "capacity", e);
            return 0;
        }
    }
}
