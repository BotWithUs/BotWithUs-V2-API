package net.botwithus.rs3.cache.assets.locs;

import net.botwithus.rs3.cache.assets.ConfigLoader;
import net.botwithus.rs3.cache.utils.ByteBufferUtils;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public final class LocLoader implements ConfigLoader<LocType> {
    @Override
    public void load(LocType type, ByteBuffer buffer) {
        while (buffer.hasRemaining()) {
            int opcode = Byte.toUnsignedInt(buffer.get());
            if (opcode == 0) {
                break;
            } else if (opcode == 1) {
                int i_4_ = Byte.toUnsignedInt(buffer.get());
                type.types = new int[i_4_];
                type.modelIds = new int[i_4_][];
                for (int index = 0; index < i_4_; index++) {
                    type.types[index] = buffer.get();
                    int i_6_ = Byte.toUnsignedInt(buffer.get());
                    type.modelIds[index] = new int[i_6_];
                    for (int index2 = 0; index2 < i_6_; index2++) {
                        type.modelIds[index][index2] = ByteBufferUtils.gSmart2or4(buffer);
                    }
                }
            } else if (opcode == 2) {
                type.name = ByteBufferUtils.readCP1252EncodedString(buffer);
            } else if (opcode == 14) {
                type.sizeX = Byte.toUnsignedInt(buffer.get());
            } else if (opcode == 15) {
                type.sizeY = Byte.toUnsignedInt(buffer.get());
            } else if (opcode == 17) {
                type.solidType = 0;
                type.blocksProjectile = false;
            } else if (opcode == 18) {
                type.blocksProjectile = false;
            } else if (opcode == 19) {
                type.interactable = Byte.toUnsignedInt(buffer.get());
            } else if (opcode == 21) {
                type.groundContoured = (byte) 1;
            } else if (opcode == 22) {
                type.delayShading = true;
            } else if (opcode == 23) {
                type.occludes = 1;
            } else if (opcode == 24) {
                int i_8_ = ByteBufferUtils.gSmart2or4(buffer);
                if (i_8_ != -1) {
                    type.animations = new int[]{i_8_};
                }
            } else if (opcode == 27) {
                type.solidType = 1;
            } else if (opcode == 28) {
                type.decorDisplacement = Byte.toUnsignedInt(buffer.get()) << 2;
            } else if (opcode == 29) {
                type.ambient = buffer.get();
            } else if (opcode == 39) {
                type.contrast = buffer.get();
            } else if (opcode >= 30 && opcode <= 34) {
                type.options[opcode - 30] = ByteBufferUtils.readCP1252EncodedString(buffer);
            } else if (opcode == 40) {
                int i_9_ = Byte.toUnsignedInt(buffer.get());
                type.originalColors = new short[i_9_];
                type.modifiedColors = new short[i_9_];
                for (int i_10_ = 0; i_10_ < i_9_; i_10_++) {
                    type.originalColors[i_10_] = (short) Short.toUnsignedInt(buffer.getShort());
                    type.modifiedColors[i_10_] = (short) Short.toUnsignedInt(buffer.getShort());
                }
            } else if (opcode == 41) {
                int i_11_ = Byte.toUnsignedInt(buffer.get());
                type.originalTextures = new short[i_11_];
                type.modifiedTextures = new short[i_11_];
                for (int i_12_ = 0; i_12_ < i_11_; i_12_++) {
                    type.originalTextures[i_12_] = buffer.getShort();
                    type.modifiedTextures[i_12_] = buffer.getShort();
                }
            } else if (opcode == 42) {
                int i_13_ = Byte.toUnsignedInt(buffer.get());
                for (int i_14_ = 0; i_14_ < i_13_; i_14_++) {
                    buffer.get();
                }
            } else if (opcode == 44) {
                buffer.getShort();
            } else if (opcode == 45) {
                buffer.getShort();
            } else if (opcode == 62) {
                type.inverted = true;
            } else if (opcode == 64) {
                type.castsShadow = false;
            } else if (opcode == 65) {
                type.scaleX = buffer.getShort();
            } else if (opcode == 66) {
                type.scaleY = buffer.getShort();
            } else if (opcode == 67) {
                type.scaleZ = buffer.getShort();
            } else if (opcode == 69) {
                type.accessBlockFlag = Byte.toUnsignedInt(buffer.get());
            } else if (opcode == 70) {
                type.offsetX = buffer.getShort() << 2;
            } else if (opcode == 71) {
                type.offsetY = buffer.getShort() << 2;
            } else if (opcode == 72) {
                type.offsetZ = buffer.getShort() << 2;
            } else if (opcode == 73) {
                type.obstructsGround = true;
            } else if (opcode == 74) {
                type.breakroutefinding = true;
            } else if (opcode == 75) {
                type.supportsItems = Byte.toUnsignedInt(buffer.get());
            } else if (opcode == 77 || opcode == 92) {
                type.varbit = Short.toUnsignedInt(buffer.getShort());
                if (type.varbit == 65535) {
                    type.varbit = -1;
                }
                type.varp = Short.toUnsignedInt(buffer.getShort());
                if (type.varp == 65535) {
                    type.varp = -1;
                }
                int objectId = -1;
                if (opcode == 92) {
                    objectId = ByteBufferUtils.gSmart2or4(buffer);
                }
                int transforms = ByteBufferUtils.readSmart(buffer);
                type.immitations = new int[transforms + 2];
                for (int i = 0; i <= transforms; i++) {
                    type.immitations[i] = ByteBufferUtils.gSmart2or4(buffer);
                }
                type.immitations[transforms + 1] = objectId;
            } else if (opcode == 78) {
                type.ambientSoundId = buffer.getShort();
                type.ambientSoundHearDistance = Byte.toUnsignedInt(buffer.get());
            } else if (opcode == 79) {
                buffer.getShort();
                buffer.getShort();
                type.ambientSoundHearDistance = Byte.toUnsignedInt(buffer.get());
                int i_18_ = Byte.toUnsignedInt(buffer.get());
                type.audioTracks = new int[i_18_];
                for (int i_19_ = 0; i_19_ < i_18_; i_19_++) {
                    type.audioTracks[i_19_] = buffer.getShort();
                }
            } else if (opcode == 81) {
                type.groundContoured = (byte) 2;
                buffer.get();
            } else if (opcode == 82) {
                type.hidden = true;
            } else if (opcode == 88) {
                //type.aBool5703 = false;
            } else if (opcode == 89) {
                //type.aBool5702 = false;
            } else if (opcode == 91) {
                type.members = true;
            } else if (opcode == 93) {
                type.groundContoured = (byte) 3;
                buffer.getShort();
            } else if (opcode == 94) {
                type.groundContoured = (byte) 4;
            } else if (opcode == 95) {
                type.groundContoured = (byte) 5;
                buffer.getShort();
            } else if (opcode == 97) {
                type.adjustMapSceneRotation = true;
            } else if (opcode == 98) {
                type.hasAnimation = true;
            } else if (opcode == 99) {
                buffer.get();
                buffer.getShort();
            } else if (opcode == 100) {
                buffer.get();
                buffer.getShort();
            } else if (opcode == 101) {
                type.mapSpriteRotation = Byte.toUnsignedInt(buffer.get());
            } else if (opcode == 102) {
                type.mapSpriteId = Short.toUnsignedInt(buffer.getShort());
            } else if (opcode == 103) {
                type.occludes = 0;
            } else if (opcode == 104) {
                type.ambientSoundVolume = Byte.toUnsignedInt(buffer.get());
            } else if (opcode == 105) {
                type.flipMapSprite = true;
            } else if (opcode == 106) {
                int i_20_ = Byte.toUnsignedInt(buffer.get());
                int i_21_ = 0;
                type.animations = new int[i_20_];
                type.animProbs = new int[i_20_];
                for (int i_22_ = 0; i_22_ < i_20_; i_22_++) {
                    type.animations[i_22_] = ByteBufferUtils.gSmart2or4(buffer);
                    type.animProbs[i_22_] = Byte.toUnsignedInt(buffer.get());
                    i_21_ += type.animProbs[i_22_];
                }
                for (int i_23_ = 0; i_23_ < i_20_; i_23_++) {
                    type.animProbs[i_23_] = type.animProbs[i_23_] * 65535 / i_21_;
                }
            } else if (opcode == 107) {
                type.mapIcon = buffer.getShort();
            } else if (opcode >= 150 && opcode < 155) {
                type.options[opcode - 150] = ByteBufferUtils.readCP1252EncodedString(buffer);
            } else if (opcode == 160) {
                int i_24_ = Byte.toUnsignedInt(buffer.get());
                for (int i_25_ = 0; i_25_ < i_24_; i_25_++) {
                    buffer.getShort();
                }
            } else if (opcode == 162) {
                type.groundContoured = (byte) 3;
                buffer.getInt();
            } else if (opcode == 163) {
                buffer.get();
                buffer.get();
                buffer.get();
                buffer.get();
            } else if (opcode == 164) {
                buffer.getShort();
            } else if (opcode == 165) {
                buffer.getShort();
            } else if (opcode == 166) {
                buffer.getShort();
            } else if (opcode == 167) {
                buffer.getShort();
            } else if (opcode == 168) {
                //type.aBoolean5718 = true;
            } else if (opcode == 169) {
                //type.aBoolean5692 = true;
            } else if (opcode == 170) {
                ByteBufferUtils.readSmart(buffer); //something to do with doors?
            } else if (opcode == 171) {
                ByteBufferUtils.readSmart(buffer);
            } else if (opcode == 173) {
                buffer.getShort();
                buffer.getShort();
            } else if (opcode == 177) {
                type.mapFunction = true;
            } else if (opcode == 178) {
                buffer.get();
            } else if (opcode == 186) {
                int unknown = Byte.toUnsignedInt(buffer.get());
            } else if (opcode == 188) {
                boolean unknown = true;
            } else if (opcode == 189) {
                //type.aBoolean5720 = true;
            } else if (opcode >= 190 && opcode < 195) {
                if (type.actionCursors == null) {
                    type.actionCursors = new int[5];
                    Arrays.fill(type.actionCursors, -1);
                }
                type.actionCursors[opcode - 190] = buffer.getShort();
            } else if (opcode == 196) {
                buffer.get();
            } else if (opcode == 197) {
                buffer.get();
            } else if (opcode == 198) {
                //boolean unknown = true;
            } else if (opcode == 199) {
                //boolean unknown = true;
            } else if (opcode == 201) {
                ByteBufferUtils.readSmart(buffer);
                ByteBufferUtils.readSmart(buffer);
                ByteBufferUtils.readSmart(buffer);
                ByteBufferUtils.readSmart(buffer);
                ByteBufferUtils.readSmart(buffer);
                ByteBufferUtils.readSmart(buffer);
            } else if (opcode == 202) {
                ByteBufferUtils.gSmart2or4(buffer);
            } else if (opcode == 203) {
                // IDK
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
    }
}
