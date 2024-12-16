package net.botwithus.rs3.cache.utils;

import java.util.HashMap;
import java.util.Map;

public class JStringTools {
    private static final Map<Character, Byte> UNICODE_SPECIAL_CHARS_TO_CP1252_MAP = new HashMap<>();
    private static final char[] CP1252_SPECIAL_CHARS_TO_UNICODE_MAP = {'\u20ac', '\0', '\u201a', '\u0192', '\u201e', '\u2026', '\u2020', '\u2021', '\u02c6', '\u2030', '\u0160', '\u2039', '\u0152', '\0', '\u017d', '\0', '\0', '\u2018', '\u2019', '\u201c', '\u201d', '\u2022', '\u2013', '\u2014', '\u02dc', '\u2122', '\u0161', '\u203a', '\u0153', '\0', '\u017e', '\u0178'};

    static {
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8364, (byte) -128);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8218, (byte) -126);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 402, (byte) -125);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8222, (byte) -124);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8230, (byte) -123);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8224, (byte) -122);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8225, (byte) -121);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 710, (byte) -120);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8240, (byte) -119);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 352, (byte) -118);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8249, (byte) -117);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 338, (byte) -116);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 381, (byte) -114);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8216, (byte) -111);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8217, (byte) -110);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8220, (byte) -109);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8221, (byte) -108);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8226, (byte) -107);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8211, (byte) -106);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8212, (byte) -105);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 732, (byte) -104);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8482, (byte) -103);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 353, (byte) -102);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 8250, (byte) -101);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 339, (byte) -100);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 382, (byte) -98);
        UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.put((char) 376, (byte) -97);
    }

    /**
     * Converts a byte representing a single CP1252 character into a char representing a unicode character.
     *
     * @param cp1252EncodedCharacter the byte to convert
     * @return the converted character
     * @throws IllegalArgumentException if the byte represents a non-CP1252 character
     */
    public static char CP1252ToUnicode(byte cp1252EncodedCharacter) {
        int unsignedByte = cp1252EncodedCharacter & 255;
        if (unsignedByte == 0) {
            throw new IllegalArgumentException("Non cp1252 character 0x" + Integer.toString(unsignedByte, 16) + " provided");
        }
        if (unsignedByte >= 128 && unsignedByte < 160) {
            char decodedChar = CP1252_SPECIAL_CHARS_TO_UNICODE_MAP[unsignedByte - 128];
            if (decodedChar == 0) {
                decodedChar = 63; // ASCII value for '?'
            }
            unsignedByte = decodedChar;
        }
        return (char) unsignedByte;
    }

    /**
     * Converts a byte representing a single CP1252 character into a char representing a unicode character.
     *
     * @param cp1252EncodedCharacters the byte[ to convert
     * @return a String containing the converted characters
     * @throws IllegalArgumentException if the byte represents a non-CP1252 character
     */
    public static String CP1252ToUTF8(byte[] cp1252EncodedCharacters) {
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < cp1252EncodedCharacters.length; index++) {
            byte cp1252EncodedCharacter = cp1252EncodedCharacters[index];
            int unsignedByte = cp1252EncodedCharacter & 255;
            if (unsignedByte == 0) {
                throw new IllegalArgumentException(
                        "CP1252 encoded characters cannot have a value of 0 which is what was provided at index %d of the array".formatted(
                                index));
            }
            if (unsignedByte >= 128 && unsignedByte < 160) {
                char decodedChar = CP1252_SPECIAL_CHARS_TO_UNICODE_MAP[unsignedByte - 128];
                if (decodedChar == 0) {
                    decodedChar = 63; // ASCII value for '?'
                }
                sb.append(decodedChar);
            }
        }
        return sb.toString();
    }

    /**
     * Converts a (potentially wide) {@link CharSequence} of unicode characters into a byte[] of CP1252 encoded characters.
     *
     * @param sequence the CharSequence to encode
     * @return the encoded byte array
     */
    public static byte[] UnicodeToCP1252(CharSequence sequence) {
        int length = sequence.length();
        byte[] encoded = new byte[length];
        for (int i = 0; i < length; ++i) {
            char c = sequence.charAt(i);
            if ((c == 0 || c >= 128) && (c < 160 || c > 255)) {
                encoded[i] = UNICODE_SPECIAL_CHARS_TO_CP1252_MAP.getOrDefault(c, (byte) 63);
            } else {
                encoded[i] = (byte) c;
            }
        }
        return encoded;
    }
}
