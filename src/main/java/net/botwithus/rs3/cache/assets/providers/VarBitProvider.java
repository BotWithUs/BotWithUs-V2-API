package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.*;
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

    private final CacheLibrary library;

    private final VarBitLoader loader;

    public VarBitProvider(CacheLibrary library) {
        this.library = library;
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
            ByteBuffer buffer = library.getFile(2, 69, id);
            if (buffer == null) {
                log.log(Level.WARNING, "Failed to load varbit type: " + id);
                return null;
            }
            VarBitType varbit = new VarBitType(id);
            loader.load(varbit, buffer);
            varbits.put(id, varbit);
            return varbit;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to load varbit type" + id, e);
        }
        return null;
    }

    @Override
    public int capacity() {
        return (int) library.getFileCount(2, 69, 0);
    }
}
