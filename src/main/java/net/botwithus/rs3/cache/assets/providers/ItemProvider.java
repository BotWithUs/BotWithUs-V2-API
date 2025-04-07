package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.*;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.items.ItemLoader;
import net.botwithus.rs3.cache.assets.items.ItemDefinition;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ItemProvider implements ConfigProvider<ItemDefinition> {
    private static final Logger log = Logger.getLogger(ItemProvider.class.getName());

    private final ItemLoader loader;
    private final CacheLibrary library;

    private final Map<Integer, ItemDefinition> items;

    private final ParamProvider paramProvider;

    public ItemProvider(CacheLibrary library, ParamProvider paramProvider) {
        this.loader = new ItemLoader();
        this.library = library;
        this.items = new HashMap<>();
        this.paramProvider = paramProvider;
    }

    @Override
    public String name() {
        return "item_types";
    }

    @Override
    public ItemDefinition provide(int id) {
        if (items.containsKey(id)) {
            return items.get(id);
        }
        try {
            int archiveId = id >> 8;
            int fileId = id & ((1 << 8) - 1);
            ByteBuffer file = library.getFile(19, archiveId, fileId);
            if (file == null) {
                log.log(Level.WARNING, "Failed to load reference table 19: " + id);
                return null;
            }
            ItemDefinition item = new ItemDefinition(id);
            loader.load(item, file);

            if(item.getNotedTemplate() != -1) {
                item.toNote(provide(item.getNotedItemId()));
            }

            items.put(id, item);
            return item;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to load reference table 19", e);
            return null;
        }
    }
}
