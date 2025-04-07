package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.*;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.inventories.InvLoader;
import net.botwithus.rs3.cache.assets.inventories.InventoryDefinition;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class InvProvider implements ConfigProvider<InventoryDefinition> {

    private static final Logger log = Logger.getLogger(InvProvider.class.getName());

    private final CacheLibrary library;
    private final InvLoader loader;

    private final Map<Integer, InventoryDefinition> cache;

    public InvProvider(CacheLibrary library) {
        this.library = library;
        this.loader = new InvLoader();
        this.cache = new HashMap<>();
    }

    @Override
    public String name() {
        return "inv_types";
    }

    @Override
    public InventoryDefinition provide(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        try {
            ByteBuffer buffer = library.getFile(2, 5, id);
            if (buffer == null) {
                log.log(Level.WARNING, "Failed to load inv type: " + id);
                return null;
            }
            InventoryDefinition type = new InventoryDefinition(id);
            loader.load(type, buffer);
            cache.put(id, type);
            return type;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to load inv type " + id, e);
            return null;
        }
    }
}
