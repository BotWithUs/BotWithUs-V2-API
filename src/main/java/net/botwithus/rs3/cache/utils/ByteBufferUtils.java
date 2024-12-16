package net.botwithus.rs3.cache.utils;

import java.nio.ByteBuffer;

public final class ByteBufferUtils {
    private ByteBufferUtils() {
    }

    /**
     * Gets an unsigned byte as a signed int.
     *
     * @param buffer the datastream
     * @return a value between 0 and 255
     */
    public static int g1(ByteBuffer buffer) {
        return Byte.toUnsignedInt(buffer.get());
    }

    public static String readVersionString(ByteBuffer buffer) {
        buffer.get();
        return readCP1252EncodedString(buffer);
    }

    /**
     * Gets a signed byte from the buffer.
     *
     * @param buffer the datastream
     * @return a value between -128 and 127
     */
    public static int g1s(ByteBuffer buffer) {
        return buffer.get();
    }

    /**
     * Gets an unsigned byte as a signed int.
     *
     * @param buffer the datastream
     * @return a value between 0 and 255
     */
    public static int g2(ByteBuffer buffer) {
        return (g1(buffer) << 8) | g1(buffer);
    }

    /**
     * Gets a three byte unsigned integer from the {@link ByteBuffer}.
     *
     * @param buffer the input data source
     * @return three unsigned bytes packed into a signed int
     */
    public static int g3(ByteBuffer buffer) {
        return (g1(buffer) << 16) | (g1(buffer) << 8) | g1(buffer);
    }

    /**
     * Gets an unsigned integer from the {@link ByteBuffer} as a... signed int.
     * This is not usually used in its raw form but rather broken down to preserve the unsigned nature of the value.
     *
     * @param buffer the input data source
     * @return four bytes that are unsigned packed together
     */
    public static int g4(ByteBuffer buffer) {
        return (g1(buffer) << 24) | (g1(buffer) << 16) | (g1(buffer) << 8) | g1(buffer);
    }

    public static long g8(ByteBuffer buffer) {
        long msb = Integer.toUnsignedLong(g4(buffer));
        long lsb = Integer.toUnsignedLong(g4(buffer));
        return (msb << 32) + lsb;
    }

    public static int readSmart(ByteBuffer buffer) {
        int peek = Byte.toUnsignedInt(buffer.get(buffer.position()));
        if (peek < 128) {
            return Byte.toUnsignedInt(buffer.get());
        }
        //We intentionally overflow
        return g2(buffer) - (Short.MAX_VALUE + 1);
    }

    public static int readUnknown(ByteBuffer buffer) {
        int i_102_ = 0;
        int i_103_ = 0;
        int i_104_;
        do {
            i_104_ = Byte.toUnsignedInt(buffer.get());
            i_102_ |= (i_104_ & 0x7f) << i_103_;
            i_103_ += 7;
        } while (i_104_ > 127);
        return i_102_;
    }

    /**
     * This method reads a byte or short from a ByteBuffer and returns it as an integer.
     * If the next byte in the buffer is less than or equal to 0x7F, it is read as a byte.
     * Otherwise, it is read as a short in little endian format and 0x8000 is subtracted from it.
     *
     * @param buffer The ByteBuffer to read from.
     * @return The byte or short read from the buffer as an integer.
     */
    public static int gSmart1or2s(ByteBuffer buffer) {
        // Read the next byte
        int value = buffer.get() & 0xFF;
        if (value <= 127) {
            // If the byte value is less than or equal to 0x7F, return it
            return value;
        } else {
            buffer.position(buffer.position() - 1);
            // Read from the starting position, but this time a short.
            short shortValue = buffer.getShort();
            // Swap the bytes of the short to adjust for little endian encoding.
            int swapped = ((shortValue & 0xFF) << 8) | ((shortValue & 0xFF00) >>> 8);
            // Subtract 0x8000 from the result and return it
            return swapped - 32768;
        }
    }

    public static String readCP1252EncodedString(ByteBuffer byteBuffer) {
        int origPos = byteBuffer.position();
        int length = 0;
        while (byteBuffer.get() != 0) {
            length++;
        }
        if (length == 0) {
            return "";
        }
        byte[] byteArray = new byte[length];
        byteBuffer.position(origPos);
        byteBuffer.get(byteArray);
        //This is being used to handle the fact that it's a C-style string I believe.
        //TODO confirm statement above to ensure it's not corrupting the parsing of different types.
        byteBuffer.position(byteBuffer.position() + 1);
        return new String(byteArray);
    }

    /**
     * Reads a single UTF-8 character from the given ByteBuffer.
     * <p>
     * This method reads one character from the current position in the buffer.
     * It advances the buffer's position to the start of the next character, however that can vary between 1 and 4 bytes.
     * If there are no more characters to read (i.e., the buffer's position is at or beyond its limit),
     * it aborts and returns 0
     *
     * @param buffer      the ByteBuffer to read from
     * @param bufferLimit the maximum position in the buffer that can be read
     * @return the Unicode code point of the character, otherwise 0.
     */
    public static int readUTF8Character(ByteBuffer buffer, int bufferLimit) {
        if (buffer.position() < bufferLimit) {
            buffer.mark();
            int firstByte = buffer.get() & 0xFF;
            if ((firstByte & 0b10000000) == 0) {
                // 1-byte character
                return firstByte;
            } else if ((firstByte & 0b11100000) == 0b11000000 && buffer.remaining() >= 1) {
                // 2-byte character
                return ((firstByte & 0x1F) << 6) | (buffer.get() & 0x3F);
            } else if ((firstByte & 0b11110000) == 0b11100000 && buffer.remaining() >= 2) {
                // 3-byte character
                int secondByte = buffer.get() & 0xFF;
                int thirdByte = buffer.get() & 0xFF;
                return (((firstByte & 0xF) << 6 | secondByte & 0x3F) << 6) | thirdByte & 0x3F;
            } else if ((firstByte & 0b11111000) == 0b11110000 && buffer.remaining() >= 3) {
                // 4-byte character
                int secondByte = buffer.get() & 0xFF;
                int thirdByte = buffer.get() & 0xFF;
                int fourthByte = buffer.get() & 0xFF;
                return ((((firstByte & 0x7) << 6 | secondByte & 0x3F) << 6 | thirdByte & 0x3F) << 6) | fourthByte & 0x3F;
            }
            // If we get here, the character wasn't made out of valid codepoints
            buffer.reset();
        }
        return 0;
    }

    public static int   readSmartSizeVar(ByteBuffer stream) {
        int value = 0;
        int curr = readSmart(stream);
        while (curr == Short.MAX_VALUE) {
            curr = readSmart(stream);
            value += Short.MAX_VALUE;
        }
        value += curr;
        return value;
    }

    public static int gSmart2or4(ByteBuffer buffer) {
        if (buffer.get(buffer.position()) < 0) {
            return buffer.getInt() & Integer.MAX_VALUE;
        }
        return Short.toUnsignedInt(buffer.getShort());
    }

    /**
     * Intelligently reads either an unsigned short or an unsigned int from the buffer.
     * This is the commonly used variant, the non "s" variant only differs by not having the value check at the end.
     * The base gSmart2or4 is only used when decoding gzip compressed cache files.
     *
     * @param buffer the datastream
     * @return -1 if it's meant to be, but normally a 4 byte unsigned int or a 2 byte unsigned int as an long (8 byte int).
     */
    public static int gSmart2or4s(ByteBuffer buffer) {
        byte signbyte = buffer.get(buffer.position());
        if (signbyte < 0) {
            return buffer.getInt() & Integer.MAX_VALUE;
        } else {
            int value = Short.toUnsignedInt(buffer.getShort());
            return value == Short.MAX_VALUE ? -1 : value;
        }
    }

    public static int readBigSmart(ByteBuffer buffer) {
        if (buffer.get(buffer.position()) < 0)
            return buffer.getInt() & 0x7fffffff;
        int i_96_ = Short.toUnsignedInt(buffer.getShort());
        if (i_96_ == 32767)
            return -1;
        return i_96_;
    }

    public static int gSmart1or2(ByteBuffer buffer) {
        byte peek = buffer.get(buffer.position());
        if (peek < 0)
            return buffer.getInt() & 0x7fffffff;
        return Short.toUnsignedInt(buffer.getShort());
    }
}
