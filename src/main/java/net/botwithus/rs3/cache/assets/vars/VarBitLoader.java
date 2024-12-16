package net.botwithus.rs3.cache.assets.vars;

import net.botwithus.rs3.cache.assets.ConfigLoader;

import java.nio.ByteBuffer;

public class VarBitLoader implements ConfigLoader<VarBitType> {
    @Override
    public void load(VarBitType type, ByteBuffer buffer) {
        while (buffer.hasRemaining()) {
            int opcode = Byte.toUnsignedInt(buffer.get());
            if (opcode == 0) {
                break;
            }
            switch (opcode) {
                case 1 -> {
                    type.domainType = VarDomainType.fromId(Byte.toUnsignedInt(buffer.get()));
                    type.varId = buffer.getShort() & 0xffff;
                }
                case 2 -> {
                    type.lsb = buffer.get() & 0xff;
                    type.msb = buffer.get() & 0xff;
                }
                default -> throw new RuntimeException("Unknown opcode " + opcode + ".");
            }
        }
    }
}
