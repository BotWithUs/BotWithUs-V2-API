package net.botwithus.rs3.cache.compression;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public final class BZIP2Compression {
    private BZIP2Compression() {
    }

    public static byte[] decompress(byte[] compressed) throws IOException {
        byte[] header = new byte[compressed.length + 4];
        System.arraycopy(compressed, 0, header, 4, compressed.length);
        header[0] = (byte) 'B';
        header[1] = (byte) 'Z';
        header[2] = (byte) 'h';
        header[3] = (byte) '1';

        try (BZip2CompressorInputStream bzIn = new BZip2CompressorInputStream(new ByteArrayInputStream(header))) {
            return bzIn.readAllBytes();
        }
    }

    /**
     * TODO this looks incomplete, we should verify this correctly compresses the data back to it's original state.
     * @param uncompressed
     * @return
     * @throws IOException
     */
    public static byte[] compress(byte[] uncompressed) throws IOException {
        byte[] buffer;
        try (ByteArrayInputStream input = new ByteArrayInputStream(uncompressed);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             BZip2CompressorOutputStream bzip2 = new BZip2CompressorOutputStream(baos, 1)) {
            byte[] block = new byte[4096];
            int len;
            while ((len = input.read(block)) != -1) {
                bzip2.write(block, 0, len);
            }
            bzip2.flush();
            buffer = baos.toByteArray();
        }

        // Strip the BZIP header off
        byte[] stripped = Arrays.copyOfRange(buffer, 4, buffer.length);

        return stripped;
    }
}
