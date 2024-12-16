package net.botwithus.rs3.cache.compression;

import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class LZMACompression {
    private LZMACompression() {
    }

    /**
     * Decompresses a byte array using LZMA compression.
     * This is currently broken I believe.
     * TODO Test and confirm
     *
     * @param compressed
     * @return
     * @throws Exception
     */
    public static byte[] decompress(byte[] compressed) throws Exception {
        byte[] properties = new byte[1];
        byte[] dictionarySize = new byte[4];
        byte[] body = new byte[compressed.length - 5];
        System.arraycopy(compressed, 0, properties, 0, properties.length);
        System.arraycopy(compressed, 1, dictionarySize, 0, dictionarySize.length);
        System.arraycopy(compressed, 5, body, 0, body.length);
        try (InputStream in = new ByteArrayInputStream(properties);
             LZMACompressorInputStream lzmaIn = new LZMACompressorInputStream(in)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len;
            while ((len = lzmaIn.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            lzmaIn.close();
            return out.toByteArray();
        }
    }

    public static byte[] compress(byte[] uncompressed) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        LZMACompressorOutputStream lzmaOut = new LZMACompressorOutputStream(out);
        lzmaOut.write(uncompressed);
        lzmaOut.close();
        return out.toByteArray();
    }
}
