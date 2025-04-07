package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.*;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.npcs.NpcLoader;
import net.botwithus.rs3.cache.assets.npcs.NpcDefinition;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NpcProvider implements ConfigProvider<NpcDefinition> {

    private static final Logger log = Logger.getLogger(NpcProvider.class.getName());

    private final Map<Integer, NpcDefinition> npcs;
    private final CacheLibrary library;

    private final NpcLoader loader;

    public NpcProvider(CacheLibrary library) {
        this.library = library;
        this.npcs = new HashMap<>();
        this.loader = new NpcLoader();
    }

    @Override
    public String name() {
        return "npc_types";
    }

    @Override
    public NpcDefinition provide(int id) {
        if (npcs.containsKey(id)) {
            return npcs.get(id);
        }
        try {
            int archiveId = id >> 7;
            int fileId = id & ((1 << 7) - 1);
            ByteBuffer buffer = library.getFile(18, archiveId, fileId);
            if (buffer == null) {
                log.log(Level.WARNING, "Failed to load npc type: " + id);
                return null;
            }
            NpcDefinition npc = new NpcDefinition(id);
            loader.load(npc, buffer);
            npcs.put(id, npc);
            return npc;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to load reference table", e);
        }

        return null;
    }
}
