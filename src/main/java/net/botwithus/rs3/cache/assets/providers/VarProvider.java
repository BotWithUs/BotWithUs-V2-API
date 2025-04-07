package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.*;
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

    private final CacheLibrary library;

    private final VarLoader loader;

    public VarProvider(CacheLibrary library) {
        this.library = library;
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
            ByteBuffer buffer = library.getFile(2, 60, id);
            if (buffer == null) {
                log.warning("Failed to load var type: " + id);
                return null;
            }
            VarType var = new VarType(id);
            loader.load(var, buffer);
            vars.put(id, var);
            return var;
        } catch (Exception e) {
            log.log(java.util.logging.Level.SEVERE, "Failed to load var type", e);
        }
        return null;
    }

    @Override
    public int capacity() {
        return (int) library.getFileCount(2, 60, 0);
    }
}
