package net.botwithus.rs3.cache.assets.components;

import net.botwithus.rs3.cache.assets.ConfigLoader;
import net.botwithus.rs3.cache.utils.ByteBufferUtils;

import java.nio.ByteBuffer;
import java.util.Arrays;

public final class ComponentLoader implements ConfigLoader<ComponentDefinition> {
    @Override
    public void load(ComponentDefinition type, ByteBuffer buffer) {
        int version = Byte.toUnsignedInt(buffer.get());
        if (version == 255) {
            version = -1;
        }
        type.type = Byte.toUnsignedInt(buffer.get());
        if ((type.type & 0x80) != 0) {
            //type.type = type.type & 0x7F;
            type.name = ByteBufferUtils.readCP1252EncodedString(buffer);
        }
        type.contentType = Short.toUnsignedInt(buffer.getShort());
        type.basePositionX = Short.toUnsignedInt(buffer.getShort());
        type.basePositionY = Short.toUnsignedInt(buffer.getShort());
        type.baseWidth = Short.toUnsignedInt(buffer.getShort());
        type.baseHeight = Short.toUnsignedInt(buffer.getShort());
        type.aspectWidthType = buffer.get();
        type.aspectHeightType = buffer.get();
        type.aspectXType = buffer.get();
        type.aspectYType = buffer.get();

        type.parentId = Short.toUnsignedInt(buffer.getShort());
        type.hidden = buffer.get() == 1;

        if (type.type == 0) {
            type.layerWidth = Short.toUnsignedInt(buffer.getShort());
            type.layerHeight = Short.toUnsignedInt(buffer.getShort());

            if ((type.layerHeight & 0x8000) != 0) {
                type.layerHeightTextTra = buffer.getInt();
            }
            if (version == -1) {
                type.disableHover = buffer.get() == 1;
            }
            if (version >= 6) {
                buffer.getInt();
            }
            if (version == 6) {
                buffer.getInt();
            }
        } else if (type.type == 3) {
            type.color = buffer.getInt();
            type.filled = buffer.get() == 1;
            type.trans = buffer.get();
        } else if (type.type == 4) {
            type.fontId = ByteBufferUtils.readBigSmart(buffer);
            if (version >= 0) {
                buffer.get();
            }
            type.text = ByteBufferUtils.readCP1252EncodedString(buffer);
            buffer.get();
            type.textHorizontalAlign = buffer.get();
            type.textVerticalAlign = buffer.get();
            type.hasShadow = buffer.get() == 1;
            type.color = buffer.getInt();
            type.trans = Byte.toUnsignedInt(buffer.get());
            if (version >= 0) {
                type.multiline = buffer.get() == 1;
            }
        } else if (type.type == 5) {
            type.spriteId = buffer.getInt();
            type.rotation = Short.toUnsignedInt(buffer.getShort());
            type.tiling = Byte.toUnsignedInt(buffer.get());
            if (type.aspectWidthType == 4) {
                type.aspectWidthData = buffer.getInt();
            }
            if (type.aspectHeightType == 4) {
                type.aspectHeightData = buffer.getInt();
            }
            type.transparency = Byte.toUnsignedInt(buffer.get());
            type.borderThickness = Byte.toUnsignedInt(buffer.get());
            buffer.getInt();
            type.vflip = buffer.get() == 1;
            type.hflip = buffer.get() == 1;
            type.color = buffer.getInt();
            if (version >= 0) {
                type.clickMask = Byte.toUnsignedInt(buffer.get());
            }
        } else if (type.type == 6) {
            type.modelId = ByteBufferUtils.readBigSmart(buffer);
            type.mode = Byte.toUnsignedInt(buffer.get());
            if (type.mode != 0) {
                type.translateX = buffer.getShort();
                type.translateY = buffer.getShort();
                if (type.mode == 2) {
                    buffer.getShort();
                }
                type.modelRotationX = buffer.getShort();
                type.modelRotationY = buffer.getShort();
                type.modelRotationZ = buffer.getShort();
                if (type.mode == 2) {
                    type.signedZoom = buffer.getShort();
                } else {
                    type.zoom = Short.toUnsignedInt(buffer.getShort());
                }
            }
            type.animationId = ByteBufferUtils.readBigSmart(buffer);
            if (type.aspectWidthType != 0) {
                type.aspectWidthData = buffer.getShort();
            }
            if (type.aspectHeightType != 0) {
                type.aspectHeightData = buffer.getShort();
            }
        } else if (type.type == 9) {
            type.lineWidth = Byte.toUnsignedInt(buffer.get());
            type.color = buffer.getInt();
            type.direction = buffer.get() == 1;
        } else if (type.type == 10) {
            buffer.position(buffer.position() + 41);
            ByteBufferUtils.readCP1252EncodedString(buffer);
            buffer.position(buffer.position() + 10);
        } else if (type.type == 11) {
            buffer.position(buffer.position() + 6);
        } else if (type.type == 12) {
            buffer.position(buffer.position() + 36);
            ByteBufferUtils.readCP1252EncodedString(buffer);
            buffer.position(buffer.position() + 10);
        } else if (type.type == 15) {
            buffer.position(buffer.position() + 10);
        } else if (type.type == 16) {
            buffer.position(buffer.position() + 179);
        }

        if (version >= 6) {
            buffer.getInt();
        }

        type.optMask = Short.toUnsignedInt(buffer.getShort());
        buffer.get();

        if (version >= 7) {
            buffer.get();
        }

        if (version >= 6) {
            buffer.get();
        }

        if (version == -1) {
            //hmmm
        }

        type.name2 = ByteBufferUtils.readCP1252EncodedString(buffer);
        type.menuCounts = Byte.toUnsignedInt(buffer.get());

        int count = type.menuCounts & 0xF;
        int optMask = type.menuCounts >> 4;

        if (count > 0) {
            type.options = new String[count];
            for (int i = 0; i < count; i++) {
                type.options[i] = ByteBufferUtils.readCP1252EncodedString(buffer);
            }
        }

        if (optMask > 0) {
            int cursorCount = Byte.toUnsignedInt(buffer.get());
            type.cursors = new int[cursorCount + 1];
            Arrays.fill(type.cursors, -1);
            type.cursors[cursorCount] = Short.toUnsignedInt(buffer.getShort());
        }
        if(optMask > 1) {
            int cursorCount = Byte.toUnsignedInt(buffer.get());
            type.cursors[cursorCount] = Short.toUnsignedInt(buffer.getShort());
        }



    }
}
