package net.botwithus.rs3.cache.assets.items;

import net.botwithus.rs3.cache.assets.ConfigLoader;
import net.botwithus.rs3.cache.utils.ByteBufferUtils;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemLoader implements ConfigLoader<ItemDefinition> {

    private static final Logger log = Logger.getLogger(ItemLoader.class.getName());

    @Override
    public void load(ItemDefinition type, ByteBuffer buffer) {
        try {
            while (buffer.hasRemaining()) {
                int opcode = Byte.toUnsignedInt(buffer.get());
                if (opcode == 0) {
                    break;
                } else if (opcode == 1) {
                    type.modelId = ByteBufferUtils.gSmart2or4s(buffer);
                } else if (opcode == 2) {
                    type.name = ByteBufferUtils.readCP1252EncodedString(buffer);
                } else if (opcode == 3) {
                    type.buffEffect = ByteBufferUtils.readCP1252EncodedString(buffer);
                } else if (opcode == 4) {
                    type.modelZoom = buffer.getShort() & 0xffff;
                } else if (opcode == 5) {
                    type.modelRotationX = buffer.getShort() & 0xffff;
                } else if (opcode == 6) {
                    type.modelRotationY = buffer.getShort() & 0xffff;
                } else if (opcode == 7) {
                    type.modelOffsetX = buffer.getShort() & 0xffff;
                    if (type.modelOffsetX > 32767) {
                        type.modelOffsetX -= 65536;
                    }
                } else if (opcode == 8) {
                    type.modelOffsetY = buffer.getShort() & 0xffff;
                    if (type.modelOffsetY > 32767) {
                        type.modelOffsetY -= 65536;
                    }
                } else if (opcode == 11) {
                    type.stackable = 1;
                } else if (opcode == 12) { // Deperacted
                    type.price = buffer.getInt();
                } else if (opcode == 13) {
                    type.wearpos = buffer.get();
                } else if (opcode == 14) {
                    type.wearpos2 = buffer.get();
                } else if (opcode == 15) {
                    // TODO bool
                } else if (opcode == 16) {
                    type.members = true;
                } else if (opcode == 18) {
                    //This is used by the engine when determining which model to render for stackable items.
                    //Think of how depending on the amount of coins you have, it's rendered differently
                    type.multistackSize = Short.toUnsignedInt(buffer.getShort());
                } else if (opcode == 23) {
                    type.maleModel1 = ByteBufferUtils.gSmart2or4s(buffer);
                } else if (opcode == 24) {
                    type.maleModel2 = ByteBufferUtils.gSmart2or4s(buffer);
                } else if (opcode == 25) {
                    type.femaleModel1 = ByteBufferUtils.gSmart2or4s(buffer);
                } else if (opcode == 26) {
                    type.femaleModel2 = ByteBufferUtils.gSmart2or4s(buffer);
                } else if (opcode == 27) {
                    type.wearpos3 = buffer.get();
                } else if (opcode >= 30 && opcode <= 34) {
                    type.groundActions[opcode - 30] = ByteBufferUtils.readCP1252EncodedString(buffer);
                } else if (opcode >= 35 && opcode <= 39) {
                    type.inventoryActions[opcode - 35] = ByteBufferUtils.readCP1252EncodedString(buffer);
                } else if (opcode == 40) {
                    int count = ByteBufferUtils.readSmart(buffer);
                    type.originalColors = new short[count];
                    type.replacementColors = new short[count];
                    for (int idx = 0; idx < count; idx++) {
                        type.originalColors[idx] = (short) (buffer.getShort() & 0xffff);
                        type.replacementColors[idx] = (short) (buffer.getShort() & 0xffff);
                    }
                } else if (opcode == 41) {
                    int count = ByteBufferUtils.readSmart(buffer);
                    type.originalTextures = new short[count];
                    type.replacementTextures = new short[count];
                    for (int idx = 0; idx < count; idx++) {
                        type.originalTextures[idx] = (short) (buffer.getShort() & 0xffff);
                        type.replacementTextures[idx] = (short) (buffer.getShort() & 0xffff);
                    }
                } else if (opcode == 42) {
                    int count = ByteBufferUtils.readSmart(buffer);
                    type.recolorPalette = new byte[count];
                    for (int index = 0; index < count; index++) {
                        type.recolorPalette[index] = buffer.get();
                    }
                } else if (opcode == 43) {
                    buffer.getInt();
                } else if (opcode == 44) {
                    buffer.getShort();
                } else if (opcode == 45) {
                    buffer.getShort();
                } else if (opcode == 65) {
                    type.exchangeable = true;
                } else if (opcode == 69) {
                    type.geBuyLimit = buffer.getInt();
                } else if (opcode == 78) {
                    type.maleModel3 = ByteBufferUtils.gSmart2or4(buffer);
                } else if (opcode == 79) {
                    type.femaleModel3 = ByteBufferUtils.gSmart2or4(buffer);
                } else if (opcode == 90) {
                    type.maleHeadModel1 = ByteBufferUtils.gSmart2or4(buffer);
                } else if (opcode == 91) {
                    type.femaleHeadModel1 = ByteBufferUtils.gSmart2or4(buffer);
                } else if (opcode == 92) {
                    type.maleHeadModel2 = ByteBufferUtils.gSmart2or4(buffer);
                } else if (opcode == 93) {
                    type.femaleHeadModel2 = ByteBufferUtils.gSmart2or4(buffer);
                } else if (opcode == 94) {
                    type.category = Short.toUnsignedInt(buffer.getShort());
                } else if (opcode == 95) {
                    type.modelAngleZ = buffer.getShort() & 0xffff;
                } else if (opcode == 96) {
                    type.searchable = Byte.toUnsignedInt(buffer.get());
                } else if (opcode == 97) {
                    type.notedItemId = buffer.getShort() & 0xffff;
                } else if (opcode == 98) {
                    type.notedTemplate = buffer.getShort() & 0xffff;
                } else if (opcode >= 100 && opcode <= 109) {
                    if (type.stackIds == null) {
                        type.stackAmounts = new int[10];
                        type.stackIds = new int[10];
                    }
                    type.stackIds[opcode - 100] = buffer.getShort() & 0xffff;
                    type.stackAmounts[opcode - 100] = buffer.getShort() & 0xffff;
                } else if (opcode == 110) {
                    type.resizeX = buffer.getShort() & 0xffff;
                } else if (opcode == 111) {
                    type.resizeY = buffer.getShort() & 0xffff;
                } else if (opcode == 112) {
                    type.resizeZ = buffer.getShort() & 0xffff;
                } else if (opcode == 113) {
                    type.ambient = buffer.get();
                } else if (opcode == 114) {
                    type.contrast = buffer.get() * 5;
                } else if (opcode == 115) {
                    type.teamId = (byte) (ByteBufferUtils.readSmart(buffer));
                } else if (opcode == 121) {
                    type.lentItemId = buffer.getShort() & 0xffff;
                } else if (opcode == 122) {
                    type.lendTemplate = buffer.getShort() & 0xffff;
                } else if (opcode == 125) {
                    type.maleModelOffsetX = buffer.get() << 2;
                    type.maleModelOffsetY = buffer.get() << 2;
                    type.maleModelOffsetZ = buffer.get() << 2;
                } else if (opcode == 126) {
                    type.femaleModelOffsetX = buffer.get() << 2;
                    type.femaleModelOffsetY = buffer.get() << 2;
                    type.femaleModelOffsetZ = buffer.get() << 2;
                } else if (opcode == 127 || opcode == 128 || opcode == 129 || opcode == 130) {
                    buffer.get();
                    buffer.getShort();
                } else if (opcode == 132) {
                    int count = ByteBufferUtils.readSmart(buffer);
                    type.questIds = new int[count];
                    for (int index = 0; index < count; index++) {
                        type.questIds[index] = buffer.getShort() & 0xffff;
                    }
                } else if (opcode == 134) {
                    type.pickSizeShift = ByteBufferUtils.readSmart(buffer);
                } else if (opcode == 139) {
                    type.bindId = buffer.getShort() & 0xffff;
                } else if (opcode == 140) {
                    type.boundTemplate = buffer.getShort() & 0xffff;
                } else if (opcode >= 142 && opcode <= 146) {
                    if (type.groundCursors == null) {
                        type.groundCursors = new int[6];
                        Arrays.fill(type.groundCursors, -1);
                    }
                    type.groundCursors[opcode - 142] = buffer.getShort() & 0xffff;
                } else if (opcode == 147) {
                    buffer.getShort();
                } else if (opcode >= 150 && opcode <= 154) {
                    if (type.inventoryCursors == null) {
                        type.inventoryCursors = new int[5];
                        Arrays.fill(type.inventoryCursors, -1);
                    }
                    type.inventoryCursors[opcode - 150] = buffer.getShort() & 0xffff;
                } else if (opcode == 157) {
                    type.randomizeGroundPos = true;
                } else if (opcode == 161) {
                    type.shardItemId = buffer.getShort() & 0xffff;
                } else if (opcode == 162) {
                    type.shardTemplateId = buffer.getShort() & 0xffff;
                } else if (opcode == 163) {
                    type.shardCombineAmount = buffer.getShort();
                } else if (opcode == 164) {
                    type.shardName = ByteBufferUtils.readCP1252EncodedString(buffer);
                } else if (opcode == 165) {
                    type.neverStackable = true;
                } else if (opcode == 167) {
                    // TODO: bool
                } else if (opcode == 168) {
                    // TODO: bool
                } else if (opcode == 181) {
                    type.price = buffer.getLong();
                } else if (opcode == 242) {
                    ByteBufferUtils.gSmart2or4(buffer);
                    ByteBufferUtils.gSmart2or4(buffer);
                } else if (opcode >= 243 && opcode <= 248) {
                    ByteBufferUtils.gSmart2or4(buffer);
                } else if (opcode == 249) {
                    int size = ByteBufferUtils.g1(buffer);
                    type.params = new ConcurrentHashMap<>(size);
                    for (int i = 0; i < size; i++) {
                        boolean isString = buffer.get() == 1;
                        int key = ByteBufferUtils.g3(buffer);
                        Serializable value = isString ? ByteBufferUtils.readCP1252EncodedString(buffer) : buffer.getInt();
                        type.params.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error loading item type", e);
        }
    }
}
