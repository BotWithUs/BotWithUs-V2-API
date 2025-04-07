package net.botwithus.rs3.cache.assets.providers;

import net.botwithus.rs3.cache.*;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.maps.TileLoader;
import net.botwithus.rs3.cache.assets.maps.SceneObjectSpawnLoader;
import net.botwithus.rs3.cache.assets.maps.RegionDefinition;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MapProvider implements ConfigProvider<RegionDefinition> {

    private static final Logger log = Logger.getLogger(MapProvider.class.getName());

    private static final int LOC_FILE = 0;
    private static final int TILES_FILE = 3;

    private final CacheLibrary library;
    private final Map<Integer, RegionDefinition> cache;

    private final SceneObjectSpawnLoader spawnLoader;
    private final TileLoader tileLoader;;

    public MapProvider(CacheLibrary library) {
        this.library = library;
        this.cache = new HashMap<>();
        this.spawnLoader = new SceneObjectSpawnLoader();
        this.tileLoader = new TileLoader();
    }

    @Override
    public String name() {
        return "region_types";
    }

    @Override
    public RegionDefinition provide(int id) {
        if(cache.containsKey(id)) {
            return cache.get(id);
        }
        try {
            int regionX = id >> 8 & 0xFF;
            int regionY = id & 0xFF;
            int archiveId = regionX | (regionY << 7);
            ByteBuffer locFile = library.getFile(5, archiveId, LOC_FILE);
            ByteBuffer tilesFile = library.getFile(5, archiveId, TILES_FILE);
            RegionDefinition type = new RegionDefinition(id);
            if (locFile != null) {
                spawnLoader.load(type, locFile);
            }
            if (tilesFile != null) {
                tileLoader.load(type, tilesFile);
            }
            cache.put(id, type);
            return type;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to load region_types reference table", e);
        }
        return null;
    }
}
