
package net.botwithus.rs3.cache.assets.cs2;

import net.botwithus.rs3.cache.assets.cs2.base.*;
import net.botwithus.rs3.cache.utils.ByteBufferUtils;

import java.nio.ByteBuffer;

public enum BaseVarType {
    INTEGER {
        @Override
        public Object decodeType(ByteBuffer buffer) {
            return buffer.getInt();
        }
    },
    LONG {
        @Override
        public Object decodeType(ByteBuffer buffer) {
            return buffer.getLong();
        }
    },
    STRING {
        @Override
        public Object decodeType(ByteBuffer buffer) {
            return ByteBufferUtils.readCP1252EncodedString(buffer);
        }
    },
    COORDFINE {
        @Override
        public Object decodeType(ByteBuffer buffer) {
            int v1 = Byte.toUnsignedInt(buffer.get());
            int v2 = buffer.getInt();
            int v3 = buffer.getInt();
            int v4 = buffer.getInt();
            return new CoordFine(v1, v2, v3, v4);
        }
    },
    UNKNOWN_TYPE {
        @Override
        public Object decodeType(ByteBuffer buffer) {
            int i_7_ = Byte.toUnsignedInt(buffer.get());
            if (0 == i_7_)
                return null;
            i_7_--;
            buffer.get(); // skip 1 byte
            int i_8_ = buffer.getInt();
            Object[] objects = new Object[i_7_];
            for (int i_9_ = 0; i_9_ < i_7_; i_9_++) {
                int i_10_ = Byte.toUnsignedInt(buffer.get());
                if (0 == i_10_)
                    objects[i_9_] = buffer.getInt();
                else if (1 == i_10_)
                    objects[i_9_] = ByteBufferUtils.readCP1252EncodedString(buffer);
                else
                    throw new IllegalStateException("Unrecognised type ID in deserialise: " + i_10_);
            }
            return new UnknownType(i_8_, objects);
        }
    },
    FLOAT {
        @Override
        public Object decodeType(ByteBuffer buffer) {
            return buffer.getFloat();
        }
    },
    UNKNOWN_TYPE1 {
        @Override
        public Object decodeType(ByteBuffer buffer) {
            return new UnknownType1(buffer.getFloat(), buffer.getFloat(), buffer.getFloat());
        }
    },
    UNSIGNED_SHORT {
        @Override
        public Object decodeType(ByteBuffer buffer) {
            return Short.toUnsignedInt(buffer.getShort());
        }
    },
    UNKNOWN_TYPE2 {
        @Override
        public Object decodeType(ByteBuffer buffer) {
            int v1 = Short.toUnsignedInt(buffer.getShort());
            int v2 = buffer.getShort();
            int v3 = buffer.getShort();
            int v4 = buffer.getShort();
            int v5 = Short.toUnsignedInt(buffer.getShort());
            return new UnknownType2(v1, v2, v3, v4, v5);
        }
    },
    UNKNOWN_TYPE3 {
        @Override
        public Object decodeType(ByteBuffer buffer) {
            float v1 = buffer.getFloat();
            float v2 = buffer.getFloat();
            return new UnknownType3(v1, v2);
        }
    };

    public abstract Object decodeType(ByteBuffer buffer);
}
