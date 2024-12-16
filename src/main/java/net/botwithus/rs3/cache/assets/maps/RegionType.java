package net.botwithus.rs3.cache.assets.maps;

import net.botwithus.rs3.cache.assets.ConfigType;

import java.util.ArrayList;
import java.util.List;

public class RegionType implements ConfigType {

    final int regionId;
    final List<LocSpawnType> spawns;
    final byte[][][] flags;

    public RegionType(int regionId) {
        this.regionId = regionId;
        this.spawns = new ArrayList<>();
        this.flags = new byte[4][64][64];
    }

    @Override
    public int getId() {
        return regionId;
    }
}
