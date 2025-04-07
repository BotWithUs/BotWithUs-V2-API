package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.*;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.so.SceneObjectLoader;
import net.botwithus.rs3.cache.assets.so.SceneObjectDefinition;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SceneObjectProvider implements ConfigProvider<SceneObjectDefinition> {

    private static final Logger log = Logger.getLogger(SceneObjectProvider.class.getName());

    private final CacheLibrary library;
    private final SceneObjectLoader loader;

    private final Map<Integer, SceneObjectDefinition> cache;

    public SceneObjectProvider(CacheLibrary library) {
        this.library = library;
        this.loader = new SceneObjectLoader();
        this.cache = new HashMap<>();
    }

    @Override
    public String name() {
        return "loc_types";
    }

    @Override
    public SceneObjectDefinition provide(int id) {
        try {
            int archiveId = id >> 8;
            int fileId = id & ((1 << 8) - 1);
            ByteBuffer buffer = library.getFile(16, archiveId, fileId);
            if (buffer == null) {
                log.log(Level.WARNING, "Failed to load loc type: " + id);
                return null;
            }
            SceneObjectDefinition type = new SceneObjectDefinition(id);
            loader.load(type, buffer);
            cache.put(id, type);
            return type;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to load loc type " + id, e);
            return null;
        }
    }
}
