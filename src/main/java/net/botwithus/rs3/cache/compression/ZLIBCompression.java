package net.botwithus.rs3.cache.compression;

import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class ZLIBCompression {
    private ZLIBCompression() {
    }

    public static byte[] decompress(byte[] compressed, int uncompressedSize) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(compressed);
        byte[] infData = new byte[uncompressedSize];
        inflater.inflate(infData);
        return infData;
    }
}
