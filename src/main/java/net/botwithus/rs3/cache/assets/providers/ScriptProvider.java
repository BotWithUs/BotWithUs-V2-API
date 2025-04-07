package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.*;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.cs2.ScriptLoader;
import net.botwithus.rs3.cache.assets.cs2.ScriptType;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ScriptProvider implements ConfigProvider<ScriptType> {

    private Map<Integer, ScriptType> cache;
    private CacheLibrary library;

    private ScriptLoader loader;

    public ScriptProvider(CacheLibrary library) {
        this.library = library;
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
            ByteBuffer buffer = library.getFile(12, id, 0);
            if (buffer == null) {
                return null;
            }
            ScriptType script = new ScriptType(id);
            loader.load(script, buffer);
            cache.put(id, script);
            return script;
        } catch (Exception e) {
            return null;
        }
    }
}
