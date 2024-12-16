package net.botwithus.rs3.cache.assets.vars;

import net.botwithus.rs3.cache.assets.ConfigLoader;
import net.botwithus.rs3.cache.assets.cs2.ScriptVarType;
import net.botwithus.rs3.cache.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

public class VarLoader implements ConfigLoader<VarType> {
    @Override
    public void load(VarType type, ByteBuffer buffer) {
        while(buffer.hasRemaining()) {
            int opcode = Byte.toUnsignedInt(buffer.get());
            if(opcode == 0) {
                break;
            }
            switch(opcode) {
                case 1 -> ByteBufferUtils.readCP1252EncodedString(buffer);
                case 3 -> {
                    int scriptVarID = Byte.toUnsignedInt(buffer.get());
                    type.type = ScriptVarType.getScriptVarTypeById(scriptVarID);
                }
                case 4, 5 -> buffer.get();
                case 7 -> {}
                case 110 -> buffer.getShort();
                default -> throw new RuntimeException("Unknown opcode " + opcode + ".");
            }
        }
    }
}
