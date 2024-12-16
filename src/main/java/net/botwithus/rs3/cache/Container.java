package net.botwithus.rs3.cache;
import net.botwithus.rs3.cache.compression.*;

import java.nio.ByteBuffer;

public class Container {
    private byte[] data;
    private ContainerCompression compression;
    private int version;

    public Container(byte[] data, ContainerCompression compression, int version) {
        this.data = data;
        this.compression = compression;
        this.version = version;
    }

    public Container(byte[] data, ContainerCompression compression) {
        this(data, compression, -1);
    }

    public Container(byte[] data) {
        this(data, ContainerCompression.LZMA, -1);
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public ContainerCompression getCompression() {
        return compression;
    }

    public void setCompression(ContainerCompression compression) {
        this.compression = compression;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public static Container decode(ByteBuffer data) throws Exception {
        if (!data.hasRemaining()) {
            throw new IllegalArgumentException("Cannot decode an empty buffer.");
        }
        if (data.remaining() >= 4) {
            char z = (char) (data.get() & 0xFF);
            char l = (char) (data.get() & 0xFF);
            char b = (char) (data.get() & 0xFF);
            int level = data.get() & 0xFF;
            if (z == 'Z' && l == 'L' && b == 'B' && level == 0x1) {
                int uncompressedSize = data.getInt();
                byte[] compressedData = new byte[data.remaining()];
                data.get(compressedData);
                byte[] uncompressedData = ZLIBCompression.decompress(compressedData, uncompressedSize);
                assert uncompressedSize == uncompressedData.length;
                return new Container(uncompressedData);
            } else {
                ContainerCompression compression = ContainerCompression.of(data.get() & 0xFF);
                int size = data.getInt();
                int decompressedSize = (compression == ContainerCompression.NONE) ? 0 : data.getInt();
                byte[] compressed = new byte[size];
                data.get(compressed);
                int version = (data.remaining() >= 2) ? data.getShort() & 0xffff : -1;
                byte[] decompressed = switch (compression) {
                    case NONE -> compressed;
                    case BZIP2 -> BZIP2Compression.decompress(compressed);
                    case GZIP -> GZIPCompression.decompress(compressed);
                    case LZMA -> LZMACompression.decompress(compressed);
                    case ZLB -> throw new UnsupportedOperationException();
                };

                return new Container(decompressed, compression, version);
            }
        }
        throw new RuntimeException("Container failed to decode.");
    }
}
