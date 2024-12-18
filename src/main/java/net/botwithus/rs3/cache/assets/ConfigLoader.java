package net.botwithus.rs3.cache.assets;

import java.nio.ByteBuffer;

public interface ConfigLoader<T extends Definition> {

    void load(T type, ByteBuffer buffer);

}
