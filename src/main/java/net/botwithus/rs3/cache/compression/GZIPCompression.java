package net.botwithus.rs3.cache.compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class GZIPCompression {
    private GZIPCompression() {
    }

    public static byte[] decompress(byte[] compressed) throws IOException {
        try (GZIPInputStream gzIn = new GZIPInputStream(new ByteArrayInputStream(compressed))) {
            return gzIn.readAllBytes();
        }
    }

    public static byte[] compress(byte[] uncompressed) throws IOException {
        try (ByteArrayInputStream input = new ByteArrayInputStream(uncompressed);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(baos)) {
            byte[] block = new byte[4096];
            int len;
            while ((len = input.read(block)) != -1) {
                gzip.write(block, 0, len);
            }
           gzip.close();
            return baos.toByteArray();
        }
    }
}
