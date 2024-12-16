package net.botwithus.rs3.cache.assets.inventories;

import net.botwithus.rs3.cache.assets.ConfigLoader;

import java.nio.ByteBuffer;

public class InvLoader implements ConfigLoader<InvType> {
    @Override
    public void load(InvType type, ByteBuffer buffer) {
        while (buffer.hasRemaining()) {
            int opcode = Byte.toUnsignedInt(buffer.get());
            if (opcode == 0) {
                break;
            }
            if (opcode == 2) {
                type.capacity = Short.toUnsignedInt(buffer.getShort());
            } else if (opcode == 4) {
                int size = Byte.toUnsignedInt(buffer.get());
                type.itemStock = new int[size];
                type.itemStockAmount = new int[size];
                for (int i = 0; i < size; i++) {
                    type.itemStock[i] = Short.toUnsignedInt(buffer.getShort());
                    type.itemStockAmount[i] = Short.toUnsignedInt(buffer.getShort());
                }
            }
        }
    }
}
