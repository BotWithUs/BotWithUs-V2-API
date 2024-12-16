package net.botwithus.rs3.cache;

import java.nio.ByteBuffer;
import java.util.SortedMap;
import java.util.TreeMap;

public class Archive {
    public final int id;
    public int name = 0;
    public int crc = 0;
    public int version = 0;
    public byte[] whirlpool = null;
    public int uncompressedSize = 0;
    public int compressedSize = 0;
    public int hash = 0;

    public boolean loaded = false;
    public SortedMap<Integer, ArchiveFile> files = new TreeMap<>();

    public Archive(int id) {
        this.id = id;
    }

    public synchronized void decodeSqlite(ByteBuffer buffer) {
        loaded = true;
        if (files.size() == 1) {
            for (ArchiveFile file : files.values()) {
                file.setData(buffer.array());
            }
            return;
        }

        int first = (buffer.get() & 0xff);
        if (first != 1) {
            throw new IllegalArgumentException("Invalid first byte (Expected 1): " + first);
        }

        int size = files.size();
        int[] ids = files.keySet().stream().mapToInt(t -> t).toArray();
        int[] offsets = new int[size + 1];
        for (int i = 0; i < size + 1; i++) {
            offsets[i] = buffer.getInt() & 0xffffff;
        }
        for (int i = 0; i < ids.length; i++) {
            int o1 = offsets[i + 1];
            int o2 = offsets[i];
            int fsize = o1 - o2;
            byte[] data = new byte[fsize];
            buffer.get(data);
            files.get(ids[i]).setData(data);
        }
    }

    /*public void decode(ByteBuffer buffer) {
        loaded = true;
        byte[] rawArray = buffer.array();
        if (files.size() == 1) {
            int firstKey = files.firstKey();
            files.get(firstKey).setData(rawArray);
            return;
        }
        int chuckCount = buffer.get() & 0xff;
        int headerLength = buffer.getInt();
        int[][] entryChunkSizes = new int[files.size()][chuckCount];
        int delta = headerLength;
        for (int chunkIndex = 0; chunkIndex < chuckCount; chunkIndex++) {
            for (int entryIndex = 0; entryIndex < files.size(); entryIndex++) {
                int prevDelta = delta;
                delta = buffer.getInt();
                int chunkSize = delta - prevDelta;
                entryChunkSizes[entryIndex][chunkIndex] = chunkSize;
            }
        }
        if (buffer.position() != headerLength) {
            throw new RuntimeException("Not all or too much header data was consumed while decoding entries. " + (headerLength - buffer.position()) + " bytes remain.");
        }
        byte[][] entryData = new byte[files.size()][];
        for (int chunkIndex = 0; chunkIndex < chuckCount; chunkIndex++) {
            for (int entryIndex = 0; entryIndex < files.size(); entryIndex++) {
                int entrySize = entryChunkSizes[entryIndex][chunkIndex];
                byte[] chunkData = new byte[entrySize];
                buffer.get(chunkData);
                if (chunkIndex == 0) {
                    entryData[entryIndex] = chunkData;
                } else {
                    entryData[entryIndex] = concatenateByteArrays(entryData[entryIndex], chunkData);
                }
            }
        }
        for (int index = 0; index < entryData.length; index++) {
            byte[] entryBytes = entryData[index];
            ArchiveFile af = files.get(index);
            if (af == null) {
                files.put(index, new ArchiveFile(index, entryBytes));
            } else {
                af.setData(entryBytes);
            }
        }
    }*/

    private static byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
