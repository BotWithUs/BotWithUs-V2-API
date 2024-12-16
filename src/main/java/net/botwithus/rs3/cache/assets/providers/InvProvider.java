package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.Archive;
import net.botwithus.rs3.cache.ArchiveFile;
import net.botwithus.rs3.cache.Filesystem;
import net.botwithus.rs3.cache.ReferenceTable;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.inventories.InvLoader;
import net.botwithus.rs3.cache.assets.inventories.InvType;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class InvProvider implements ConfigProvider<InvType> {

    private static final Logger log = Logger.getLogger(InvProvider.class.getName());

    private final Filesystem fs;
    private final InvLoader loader;

    private final Map<Integer, InvType> cache;

    public InvProvider(Filesystem fs) {
        this.fs = fs;
        this.loader = new InvLoader();
        this.cache = new HashMap<>();
    }

    @Override
    public String name() {
        return "inv_types";
    }

    @Override
    public InvType provide(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        try {
            ReferenceTable table = fs.getReferenceTable(2, false);
            if (table == null) {
                return null;
            }
            Archive archive = table.loadArchive(5);
            if (archive == null) {
                return null;
            }
            ArchiveFile file = archive.files.get(id);
            if (file == null) {
                return null;
            }
            InvType type = new InvType(id);
            loader.load(type, ByteBuffer.wrap(file.getData()));
            cache.put(id, type);
            return type;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to load inv type " + id, e);
            return null;
        }
    }
}
