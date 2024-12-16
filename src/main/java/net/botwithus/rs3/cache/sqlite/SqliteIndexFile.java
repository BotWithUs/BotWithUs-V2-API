package net.botwithus.rs3.cache.sqlite;

import java.io.Closeable;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqliteIndexFile implements Closeable {
    private static final Logger log = Logger.getLogger(SqliteIndexFile.class.getName());

    private final Connection connection;

    public SqliteIndexFile(Path path) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + path.toAbsolutePath());
    }

    public boolean isClosed() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to check if connection is closed", e);
            return true;
        }
    }

    public boolean hasReferenceTable() {
        try {
            return connection.createStatement().executeQuery("SELECT * FROM CACHE_INDEX").next();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to check if reference table exists", e);
            return false;
        }
    }

    public int getMaxArchive() {
        try {
            return connection.createStatement().executeQuery("SELECT MAX(KEY) FROM CACHE").getInt(1);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to get max archive", e);
            return -1;
        }
    }

    public boolean exists(int id) {
        try {
            return connection.createStatement().executeQuery("SELECT * FROM CACHE WHERE KEY = " + id).next();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to check if archive exists", e);
            return false;
        }
    }

    public byte[] getRaw(int id) {
        try {
            return connection.createStatement().executeQuery("SELECT DATA FROM CACHE WHERE KEY = " + id).getBytes(1);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to get archive", e);
            return null;
        }
    }

    public byte[] getRawTable() {
        try {
            return connection.createStatement().executeQuery("SELECT DATA FROM CACHE_INDEX").getBytes(1);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to get reference table", e);
            return null;
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Failed to close connection", e);
        }
    }
}
