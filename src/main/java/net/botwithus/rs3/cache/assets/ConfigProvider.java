package net.botwithus.rs3.cache.assets;

public interface ConfigProvider<T extends Definition> {
    String name();

    T provide(int id);

    default int capacity() {
        return 0;
    }

}
