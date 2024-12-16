package net.botwithus.rs3.cache.assets.cs2;

import net.botwithus.rs3.cache.assets.ConfigLoader;
import net.botwithus.rs3.cache.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

/**
 * A loader for {@link ScriptType} objects.
 * We only need to decode the header for a script to verify args etc
 * @see ScriptType
 */

public class ScriptLoader implements ConfigLoader<ScriptType> {
    @Override
    public void load(ScriptType type, ByteBuffer buffer) {
        buffer.position(buffer.capacity() - 2);
        int size = buffer.getShort() & 0xFFFF;
        int end = buffer.capacity() - 2 - size - 16;
        buffer.position(end);
        type.setInstructionCount(buffer.getInt());
        type.setLocalIntCount(buffer.getShort() & 0xFFFF);
        type.setLocalStringCount(buffer.getShort() & 0xFFFF);
        type.setLocalLongCount(buffer.getShort() & 0xFFFF);

        type.setIntArgumentCount(buffer.getShort() & 0xFFFF);
        type.setStringArgumentCount(buffer.getShort() & 0xFFFF);
        type.setLongArgumentCount(buffer.getShort() & 0xFFFF);

        type.setSwitchTableCount(buffer.get() & 0xFF);
        if (type.getSwitchTableCount() > 0) {
            decodeSwitchTable(type, buffer);
        }

        buffer.position(0);
        type.setName(ByteBufferUtils.readCP1252EncodedString(buffer));

        //for decoding instructions requires a complete unscrambled database, so for now don't expose that.

        //type.setInstructions(new Instruction[type.getInstructionCount()]);

        //decodeInstructions(type, buffer, end);
    }

    /*private void decodeInstructions(ScriptType script, ByteBuffer buffer, int end) {
        int address = 0;
        while(buffer.position() < end) {
            if(address >= script.getInstructionCount()) {
                throw new IndexOutOfBoundsException("Address is greater than instruction count");
            }
            int scrambled_opcode = buffer.getShort() & 0xFFFF;
            InstructionType type = database.getInstructionType(scrambled_opcode);
            Instruction instruction = new Instruction(type.getRawOpcode());
            int opcode = type.getRawOpcode();
            script.getInstructions()[address++] = instruction;
            instruction.setName(type.getName());
            instruction.setType(type);
            //PUSH/POP VAR
            if(opcode == 1 || opcode == 2) {
                int domain = buffer.get() & 0xFF;
                int index = buffer.getShort() & 0xFFFF;
                int idk = buffer.get() & 0xFF;
                instruction.setOperand1(domain);
                instruction.setOperand2(index);
                instruction.setOperand3(idk);
            } else if(opcode == 5) {
                int const_type = buffer.get() & 0xFF;
                switch (const_type) {
                    case 0:
                        instruction.setOperand1(buffer.getInt());
                        break;
                    case 1:
                        instruction.setLongOperand(buffer.getLong());
                        break;
                    case 2:
                        instruction.setStringOperand(ByteBufferUtils.readCP1252EncodedString(buffer));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown constant type: " + const_type);
                }
            } else if(opcode == 3 || opcode == 4) {
                int varbitID = buffer.getShort() & 0xFFFF;
                int idk = buffer.get() & 0xFF;
                instruction.setOperand1(varbitID);
                instruction.setOperand2(idk);
            } else {
                if(type.isInt()) {
                    instruction.setOperand1(buffer.getInt());
                } else {
                    instruction.setOperand1(buffer.get() & 0xFF);
                }
            }
        }
    }*/

    private void decodeSwitchTable(ScriptType script, ByteBuffer buffer) {
        int switchTableCount = script.getSwitchTableCount();
        script.setSwitchTables(new SwitchTable[switchTableCount]);
        for (int i = 0; i < switchTableCount; i++) {
            int cases_count = buffer.getShort() & 0xFFFF;
            SwitchTable switchTable = new SwitchTable(method1383(cases_count));
            script.getSwitchTables()[i] = switchTable;
            while(cases_count-- > 0) {
                int key = buffer.getInt();
                int jump = buffer.getInt();
                switchTable.setJump(key, new JumpInstruction(-1, jump));
            }
        }
    }

    private static int method1383(int i) {
        i = --i | i >>> 1;
        i |= i >>> 2;
        i |= i >>> 4;
        i |= i >>> 8;
        i |= i >>> 16;
        return 1 + i;
    }
}
