package net.botwithus.rs3.cache.assets.params;

import net.botwithus.rs3.cache.assets.ConfigLoader;
import net.botwithus.rs3.cache.assets.cs2.ScriptVarType;
import net.botwithus.rs3.cache.utils.ByteBufferUtils;
import net.botwithus.rs3.cache.utils.JStringTools;

import java.nio.ByteBuffer;

public class ParamLoader implements ConfigLoader<ParamDefinition> {
    @Override
    public void load(ParamDefinition type, ByteBuffer buffer) {
        while (buffer.hasRemaining()) {
            int opcode = Byte.toUnsignedInt(buffer.get());
            if (opcode == 0) {
                break;
            }
            if (1 == opcode) {
                type.type = ScriptVarType.getByChar(JStringTools.CP1252ToUnicode(buffer.get()));
            } else if (2 == opcode) {
                type.defaultint = buffer.getInt();
            } else if (4 == opcode) {
                type.autodisable = false;
            } else if (5 == opcode) {
                type.defaultstr = ByteBufferUtils.readCP1252EncodedString(buffer);
            } else if (101 == opcode) {
                type.type = ScriptVarType.getScriptVarTypeById(ByteBufferUtils.readSmart(buffer));
            }
        }
    }
}
