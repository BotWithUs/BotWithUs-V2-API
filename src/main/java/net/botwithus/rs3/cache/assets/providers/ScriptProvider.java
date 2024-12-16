package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.Archive;
import net.botwithus.rs3.cache.ArchiveFile;
import net.botwithus.rs3.cache.Filesystem;
import net.botwithus.rs3.cache.ReferenceTable;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.cs2.ScriptLoader;
import net.botwithus.rs3.cache.assets.cs2.ScriptType;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ScriptProvider implements ConfigProvider<ScriptType> {

    private Map<Integer, ScriptType> cache;
    private Filesystem fs;

    private ScriptLoader loader;

    public ScriptProvider(Filesystem fs) {
        this.fs = fs;
        this.cache = new HashMap<>();
        this.loader = new ScriptLoader();
    }

    @Override
    public String name() {
        return "script_types";
    }

    @Override
    public ScriptType provide(int id) {
        try {
            ReferenceTable table = fs.getReferenceTable(12, false);
            if(table == null) {
                return null;
            }
            Archive archive = table.loadArchive(id);
            if(archive == null) {
                return null;
            }
            ArchiveFile file = archive.files.getOrDefault(0, null);
            if(file == null) {
                return null;
            }
            byte[] data = file.getData();
            if(data == null) {
                return null;
            }
            ScriptType script = new ScriptType(id);
            loader.load(script, ByteBuffer.wrap(data));
            cache.put(id, script);
            return script;
        } catch (Exception e) {
            return null;
        }
    }
}
