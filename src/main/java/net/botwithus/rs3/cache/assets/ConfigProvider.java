package net.botwithus.rs3.cache.assets;

public interface ConfigProvider<T extends ConfigType> {
    String name();

    T provide(int id);

    default int capacity() {
        return 0;
    }

}
