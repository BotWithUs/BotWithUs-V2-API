package net.botwithus.rs3.cache.assets.npcs;

import net.botwithus.rs3.cache.assets.ConfigLoader;
import net.botwithus.rs3.cache.utils.ByteBufferUtils;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class NpcLoader implements ConfigLoader<NpcType> {

    private static final Logger log = Logger.getLogger(NpcLoader.class.getName());

    @Override
    public void load(NpcType type, ByteBuffer stream) {
        while (stream.hasRemaining()) {
            int opcode = ByteBufferUtils.g1(stream);
            if (opcode == 0) {
                break;
            }
            switch (opcode) {
                case 1:
                    int count = ByteBufferUtils.g1(stream);
                    type.modelIds = new int[count];
                    for (int i = 0; i < count; i++) {
                        type.modelIds[i] = ByteBufferUtils.gSmart2or4(stream);
                    }
                    break;
                case 2:
                    type.name = ByteBufferUtils.readCP1252EncodedString(stream);
                    break;
                case 12:
                    type.size = ByteBufferUtils.g1(stream);
                    break;
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                    type.options[opcode - 30] = ByteBufferUtils.readCP1252EncodedString(stream);
                    break;
                case 40:
                    count = ByteBufferUtils.g1(stream);
                    type.originalColors = new short[count];
                    type.modifiedColors = new short[count];
                    for (int i_5 = 0; i_5 < count; i_5++) {
                        type.originalColors[i_5] = stream.getShort();
                        type.modifiedColors[i_5] = stream.getShort();
                    }
                    break;
                case 41:
                    count = ByteBufferUtils.g1(stream);
                    type.originalTextures = new short[count];
                    type.modifiedTextures = new short[count];
                    for (int i_5 = 0; i_5 < count; i_5++) {
                        type.originalTextures[i_5] = stream.getShort();
                        type.modifiedTextures[i_5] = stream.getShort();
                    }
                    break;
                case 42:
                    count = ByteBufferUtils.g1(stream);
                    type.recolourPalette = new byte[count];
                    for (int i_5 = 0; i_5 < count; i_5++) {
                        type.recolourPalette[i_5] = stream.get();
                    }
                    break;
                case 44, 45, 252:
                    stream.getShort();
                    break;
                case 60:
                    count = ByteBufferUtils.g1(stream);
                    type.headModels = new int[count];
                    for (int i_5 = 0; i_5 < count; i_5++) {
                        type.headModels[i_5] = ByteBufferUtils.gSmart2or4(stream);
                    }
                    break;
                case 93:
                    type.drawMapdot = false;
                    break;
                case 95:
                    type.combatLevel = Short.toUnsignedInt(stream.getShort());
                    break;
                case 97:
                    type.resizeX = stream.getShort();
                    break;
                case 98:
                    type.resizeY = stream.getShort();
                    break;
                case 99:
                    type.aBool4904 = true;
                    break;
                case 100:
                    type.ambient = stream.get();
                    break;
                case 101:
                    type.contrast = stream.get();
                    break;
                case 102:
                    int vv = stream.get() & 0xFF;
                    int t = 0;
                    for (int i = vv; i != 0; i >>= 1) {
                        t++;
                    }
                    for (int i = 0; i < t; i++) {
                        if ((vv & 1 << i) != 0) {
                            ByteBufferUtils.gSmart2or4(stream);
                            ByteBufferUtils.readSmart(stream);
                        }
                    }
                    break;
                case 103:
                    type.rotation = stream.getShort();
                    break;
                case 106:
                case 118:
                    type.varbit = Short.toUnsignedInt(stream.getShort());
                    type.varp = Short.toUnsignedInt(stream.getShort());
                    if (type.varbit == 65535) {
                        type.varbit = -1;
                    }
                    if (type.varp == 65535) {
                        type.varp = -1;
                    }
                    int defaultId = -1;
                    if (opcode == 118) {
                        defaultId = Short.toUnsignedInt(stream.getShort());
                    }
                    int size = ByteBufferUtils.readSmart(stream);
                    type.transformTo = new int[size + 2];
                    for (int i = 0; i <= size; i++) {
                        type.transformTo[i] = Short.toUnsignedInt(stream.getShort());
                        if (type.transformTo[i] == 65535) {
                            type.transformTo[i] = -1;
                        }
                    }
                    type.transformTo[size + 1] = defaultId;
                    break;
                case 107:
                    type.visible = false;
                    break;
                case 109:
                    type.isClickable = false;
                    break;
                case 111:
                    type.animateIdle = false;
                    break;
                case 113:
                    type.aShort4874 = stream.getShort();
                    type.aShort4897 = stream.getShort();
                    break;
                case 114:
                    type.aByte4883 = stream.get();
                    type.aByte4899 = stream.get();
                    break;
                case 119:
                    type.walkMask = stream.get();
                    break;
                case 121:
                    count = ByteBufferUtils.g1(stream);
                    type.modelTranslation = new int[count][];
                    for (int i_5 = 0; i_5 < count; i_5++) {
                        int[] translations = new int[4];
                        translations[0] = ByteBufferUtils.g1(stream);
                        translations[1] = ByteBufferUtils.g1(stream);
                        translations[2] = ByteBufferUtils.g1(stream);
                        translations[3] = ByteBufferUtils.g1(stream);
                        type.modelTranslation[i_5] = translations;
                    }
                    break;
                case 123:
                    type.height = stream.getShort();
                    break;
                case 125:
                    type.respawnDirection = stream.get();
                    break;
                case 127:
                    type.basId = Short.toUnsignedInt(stream.getShort());
                    break;
                case 128:
                    type.movementType = ByteBufferUtils.g1(stream);
                    break;
                case 134:
                    type.walkingAnimation = stream.getShort();
                    type.rotate180Animation = stream.getShort();
                    type.rotate90RightAnimation = stream.getShort();
                    type.rotate90LeftAnimation = stream.getShort();
                    type.specialByte = ByteBufferUtils.g1(stream);
                    break;
                case 135:
                    type.anInt4875 = ByteBufferUtils.g1(stream);
                    type.anInt4873 = stream.getShort();
                    break;
                case 136:
                    type.anInt4854 = ByteBufferUtils.g1(stream);
                    type.anInt4861 = stream.getShort();
                    break;
                case 137:
                    type.attackOpCursor = stream.getShort();
                    break;
                case 138:
                    type.armyIcon = ByteBufferUtils.gSmart2or4(stream);
                    break;
                case 140:
                    type.anInt4909 = ByteBufferUtils.g1(stream);
                    break;
                case 141:
                    type.aBool4884 = true;
                    break;
                case 142:
                    type.mapIcon = stream.getShort();
                    break;
                case 143:
                    type.aBool4890 = true;
                    break;
                case 150:
                case 151:
                case 152:
                case 153:
                case 154:
                    type.options[opcode - 150] = ByteBufferUtils.readCP1252EncodedString(stream);
                    break;
                case 155:
                    type.aByte4868 = stream.get();
                    type.aByte4869 = stream.get();
                    type.aByte4905 = stream.get();
                    type.aByte4871 = stream.get();
                    break;
                case 158:
                    type.aByte4916 = 1;
                    break;
                case 159:
                    type.aByte4916 = 0;
                    break;
                case 160:
                    count = ByteBufferUtils.g1(stream);
                    type.quests = new int[count];
                    for (int i_5 = 0; i_5 < count; i_5++) {
                        type.quests[i_5] = stream.getShort();
                    }
                    break;
                case 162:
                    type.aBool4872 = true;
                    break;
                case 163:
                    type.anInt4917 = ByteBufferUtils.g1(stream);
                    break;
                case 164:
                    type.anInt4911 = stream.getShort();
                    type.anInt4919 = stream.getShort();
                    break;
                case 165:
                    type.anInt4913 = ByteBufferUtils.g1(stream);
                    break;
                case 168:
                    type.anInt4908 = ByteBufferUtils.g1(stream);
                    break;
                case 169:
                    type.aBool4920 = false;
                    break;
                case 170:
                case 171:
                case 172:
                case 173:
                case 174:
                case 175:
                    if (type.actionCursors == null) {
                        type.actionCursors = new int[5];
                        Arrays.fill(type.actionCursors, -1);
                    }
                    type.actionCursors[opcode - 170] = stream.getShort();
                    break;
                case 178:
                case 182:
                    // Do nothing
                    break;
                case 179:
                    ByteBufferUtils.readSmart(stream);
                    ByteBufferUtils.readSmart(stream);
                    ByteBufferUtils.readSmart(stream);
                    ByteBufferUtils.readSmart(stream);
                    ByteBufferUtils.readSmart(stream);
                    ByteBufferUtils.readSmart(stream);
                    break;
                case 180:
                    stream.get();
                    break;
                case 181:
                    stream.position(stream.position() + 3);
                    break;
                case 183:
                case 184:
                    stream.position(stream.position() + 1);
                    break;
                case 185:
                    break;
                case 253:
                    stream.get();
                    break;
                case 249:
                    int length = ByteBufferUtils.g1(stream);
                    type.params = new HashMap<>(length);
                    for (int i = 0; i < length; i++) {
                        boolean isString = ByteBufferUtils.g1(stream) == 1;
                        int key = ByteBufferUtils.g3(stream);
                        Object value = isString ? ByteBufferUtils.readCP1252EncodedString(stream) : stream.getInt();
                        type.params.put(key, value);
                    }
                    break;
            }
        }
    }
}
