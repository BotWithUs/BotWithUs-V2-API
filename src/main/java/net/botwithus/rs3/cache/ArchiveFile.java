package net.botwithus.rs3.cache;

public class ArchiveFile {
    private final int id;
    private byte[] data;
    private int name;

    public ArchiveFile(int id, byte[] data, int name) {
        this.id = id;
        this.data = data;
        this.name = name;
    }

    public ArchiveFile(int id, byte[] data) {
        this(id, data, 0);
    }

    public ArchiveFile(int id) {
        this(id, new byte[0], 0);
    }

    public int getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
