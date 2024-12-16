package net.botwithus.rs3.cache.compression;

import java.util.logging.Logger;

public enum ContainerCompression {
    NONE(0),
    BZIP2(1),
    GZIP(2),
    LZMA(3),
    ZLB(100);

    private final int id;

    ContainerCompression(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private static final Logger log = Logger.getLogger(ContainerCompression.class.getName());

    public static ContainerCompression of(int id) {
        for (ContainerCompression compression : ContainerCompression.values()) {
            if (compression.id == id) {
                return compression;
            }
        }
        log.warning("The compression type " + id + " is not defined. ");
        throw new IllegalArgumentException("No compression found for id: " + id);
    }
}
