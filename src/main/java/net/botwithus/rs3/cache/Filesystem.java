package net.botwithus.rs3.cache;
import java.nio.ByteBuffer;
import java.nio.file.Path;

public abstract class Filesystem {
    private final boolean[] checkedReferenceTables = new boolean[255];
    private final ReferenceTable[] cachedReferenceTables = new ReferenceTable[255];
    protected Path path;

    public Filesystem(Path path) {
        this.path = path;
    }

    public abstract boolean exists(int index, int archive);

    public abstract ByteBuffer read(int index, int archive);

    public abstract ByteBuffer read(int index, String name);

    public abstract ByteBuffer readReferenceTable(int index);

    public ReferenceTable getReferenceTable(int index, boolean ignoreChecked) throws Exception {
        ReferenceTable cached = cachedReferenceTables[index];
        if (cached != null) {
            return cached;
        }

        if (!ignoreChecked) {
            if (checkedReferenceTables[index]) {
                return null;
            }
            checkedReferenceTables[index] = true;
        }
        ByteBuffer container = readReferenceTable(index);
        if (container == null) {
            return null;
        }
        ReferenceTable table = new ReferenceTable(this, index);
        ByteBuffer data = ByteBuffer.wrap(Container.decode(container).getData());
        table.decode(data);
        cachedReferenceTables[index] = table;

        return table;
    }

    public abstract int numIndices();

    public abstract void close();
}
