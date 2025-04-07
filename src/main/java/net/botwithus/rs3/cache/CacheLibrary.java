package net.botwithus.rs3.cache;

import java.nio.ByteBuffer;

public interface CacheLibrary {

    ByteBuffer getFile(int indexId, int archiveId, int fileId);

    long getFileCount(int indexId, int archiveId, int shift);

}
