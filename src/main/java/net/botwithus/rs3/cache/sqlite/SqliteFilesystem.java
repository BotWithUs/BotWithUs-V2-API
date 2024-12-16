package net.botwithus.rs3.cache.sqlite;

import net.botwithus.rs3.cache.Filesystem;
import net.botwithus.rs3.cache.ReferenceTable;

import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqliteFilesystem extends Filesystem implements Closeable {

    private static final Logger log = Logger.getLogger(SqliteFilesystem.class.getName());

    private final Map<Integer, SqliteIndexFile> indices;

    public SqliteFilesystem(Path path) {
        super(path);
        this.indices = new HashMap<>();
    }

    @Override
    public boolean exists(int index, int archive) {
        if (index < 0 || index >= indices.size()) {
            throw new IndexOutOfBoundsException("index out of bounds: " + index);
        }
        SqliteIndexFile file = getOrOpen(index);
        return file != null && file.exists(archive);
    }

    @Override
    public ByteBuffer read(int index, int archive) {
        if (index < 0 || index >= 255) {
            throw new IndexOutOfBoundsException("The archive at index " + index + " is out bounds.");
        }
        SqliteIndexFile file = getOrOpen(index);
        if (file == null) {
            return null;
        }
        byte[] rawArchive = file.getRaw(archive);
        return rawArchive != null ? ByteBuffer.wrap(rawArchive) : null;
    }

    @Override
    public ByteBuffer read(int index, String name) {
        ReferenceTable table = null;
        try {
            table = getReferenceTable(index, false);
            if (table == null) {
                return null;
            }
            int hash = nameToFilesystemHash(name);
            int id = Objects.requireNonNull(
                    table.archives.entrySet().stream().filter(entry -> entry.getValue().name == hash).findFirst().orElse(null)).getKey();
            return read(index, id);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to get reference table", e);
            return null;
        }
    }

    @Override
    public ByteBuffer readReferenceTable(int index) {
        if (index < 0 || index >= 255) {
            throw new IndexOutOfBoundsException("The reference table can only be read for archive indices between %d and %d".formatted(0,255));
        }
        SqliteIndexFile file = getOrOpen(index);
        if (file == null) {
            return null;
        }
        byte[] rawTable = file.getRawTable();
        return rawTable != null ? ByteBuffer.wrap(rawTable) : null;
    }

    @Override
    public int numIndices() {
        return indices.size();
    }

    @Override
    public void close() {
        for (SqliteIndexFile value : indices.values()) {
            if (!value.isClosed()) {
                value.close();
            }
        }
    }

    private SqliteIndexFile getOrOpen(int indexId) {
        if (indexId < 0 || indexId > 255) {
            return null;
        }
        SqliteIndexFile index = indices.get(indexId);
        if(index == null) {
            try {
                index = new SqliteIndexFile(path.resolve("js5-" + indexId + ".jcache"));
                indices.put(indexId, index);
            } catch (Exception e) {
                log.warning("Unable to access the file at index " + indexId + " of archive \"" + path + "\"");
                return null;
            }
        }
        if (index.isClosed()) {
            indices.remove(indexId);
            return null;
        }
        return index;
    }

    private int nameToFilesystemHash(String str) throws UnsupportedEncodingException {
        int hash = 0;
        for (byte cp1252 : str.getBytes("Cp1252")) {
            hash = (hash << 5) - hash + cp1252;
        }
        return hash;
    }

}
