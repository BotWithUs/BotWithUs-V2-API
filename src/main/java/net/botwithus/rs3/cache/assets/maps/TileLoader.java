package net.botwithus.rs3.cache.assets.maps;

import net.botwithus.rs3.cache.assets.ConfigLoader;
import net.botwithus.rs3.cache.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

public final class TileLoader implements ConfigLoader<RegionType> {
    @Override
    public void load(RegionType type, ByteBuffer buffer) {
        buffer.position(buffer.position() + 5);
        for (int plane = 0; plane < 4; plane++) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    int flag = Byte.toUnsignedInt(buffer.get());
                    if((flag & 0x1) != 0) {
                        buffer.get();
                    }
                    if((flag & 0x2) != 0) {
                        type.flags[plane][x][y] = buffer.get();
                    }
                    if((flag & 0x4) != 0) {
                        ByteBufferUtils.readSmart(buffer);
                    }
                    if((flag & 0x8) != 0) {
                        buffer.getShort();
                    }
                }
            }
        }
    }
}
