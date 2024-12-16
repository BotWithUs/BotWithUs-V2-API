package net.botwithus.rs3.cache.assets.maps;

import net.botwithus.rs3.cache.assets.ConfigLoader;
import net.botwithus.rs3.cache.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

public final class LocSpawnLoader implements ConfigLoader<RegionType> {
    @Override
    public void load(RegionType type, ByteBuffer buffer) {
        int bx = (type.regionId >> 8) & 0xFF;
        int by = type.regionId & 0xFF;
        int objectId = -1;
        int incr;
        while ((incr = ByteBufferUtils.readSmartSizeVar(buffer)) != 0) {
            objectId += incr;
            int location = 0;
            int incr2;
            while ((incr2 = ByteBufferUtils.readSmart(buffer)) != 0) {
                location += incr2 - 1;
                final int localX = (location >> 6 & 0x3f);
                final int localY = (location & 0x3f);
                final int plane = location >> 12;
                LocCustomization objectData = new LocCustomization(buffer);
                final int shape = objectData.type;
                final int rotation = objectData.rotation;
                if (plane < 0 || plane >= 4) {
                    continue;
                }
                int x = bx * 64 + localX;
                int y = by * 64 + localY;
                type.spawns.add(new LocSpawnType(objectId, x, y, plane, shape, rotation));
            }
        }
    }
}
