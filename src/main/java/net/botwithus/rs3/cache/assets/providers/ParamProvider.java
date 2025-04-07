package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.*;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.params.ParamLoader;
import net.botwithus.rs3.cache.assets.params.ParamDefinition;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ParamProvider implements ConfigProvider<ParamDefinition> {

    private static final Logger log = Logger.getLogger(ItemProvider.class.getName());
    private final CacheLibrary library;

    private final ParamLoader loader;

    private final Map<Integer, ParamDefinition> params;

    public ParamProvider(CacheLibrary library) {
        this.library = library;
        this.params = new HashMap<>();
        this.loader = new ParamLoader();
    }

    @Override
    public String name() {
        return "param_types";
    }

    @Override
    public ParamDefinition provide(int id) {
        if (params.containsKey(id)) {
            return params.get(id);
        }
        try {
            ByteBuffer buffer = library.getFile(2, 11, id);
            if (buffer == null) {
                log.log(Level.WARNING, "Failed to load param type: " + id);
                return null;
            }
            ParamDefinition param = new ParamDefinition(id);
            loader.load(param, buffer);
            params.put(id, param);
            return param;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to load reference table", e);
            return null;
        }
    }
}
