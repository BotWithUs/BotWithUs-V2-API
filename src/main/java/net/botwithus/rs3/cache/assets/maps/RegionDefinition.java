package net.botwithus.rs3.cache.assets.maps;

import net.botwithus.rs3.cache.assets.Definition;

import java.util.ArrayList;
import java.util.List;

public class RegionDefinition implements Definition {

    final int regionId;
    final List<SceneObjectSpawnDefinition> spawns;
    final byte[][][] flags;

    public RegionDefinition(int regionId) {
        this.regionId = regionId;
        this.spawns = new ArrayList<>();
        this.flags = new byte[4][64][64];
    }

    @Override
    public int getId() {
        return regionId;
    }
}
