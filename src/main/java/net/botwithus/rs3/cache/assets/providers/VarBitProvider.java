package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.Archive;
import net.botwithus.rs3.cache.ArchiveFile;
import net.botwithus.rs3.cache.Filesystem;
import net.botwithus.rs3.cache.ReferenceTable;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.vars.VarBitLoader;
import net.botwithus.rs3.cache.assets.vars.VarBitType;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class VarBitProvider implements ConfigProvider<VarBitType> {

    private static final Logger log = Logger.getLogger(VarBitProvider.class.getName());

    private final Map<Integer, VarBitType> varbits;

    private final Filesystem fs;

    private final VarBitLoader loader;

    public VarBitProvider(Filesystem fs) {
        this.fs = fs;
        this.loader = new VarBitLoader();
        this.varbits = new HashMap<>();
    }

    @Override
    public String name() {
        return "varbit_types";
    }

    @Override
    public VarBitType provide(int id) {
        if (varbits.containsKey(id)) {
            return varbits.get(id);
        }
        try {
            ReferenceTable table = fs.getReferenceTable(2, false);
            if (table == null) {
                return null;
            }
            Archive archive = table.loadArchive(69);
            if (archive == null) {
                return null;
            }
            ArchiveFile file = archive.files.get(id);
            if (file == null) {
                return null;
            }
            VarBitType varbit = new VarBitType(id);
            loader.load(varbit, ByteBuffer.wrap(file.getData()));
            varbits.put(id, varbit);
            return varbit;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to load varbit type" + id, e);
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
            Archive archive = table.loadArchive(69);
            if (archive == null) {
                return 0;
            }
            return archive.files.size();
        } catch (Exception e) {
            return 0;
        }
    }
}
