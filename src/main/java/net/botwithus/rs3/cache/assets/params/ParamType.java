package net.botwithus.rs3.cache.assets.params;

import net.botwithus.rs3.cache.assets.ConfigType;
import net.botwithus.rs3.cache.assets.cs2.ScriptVarType;

public class ParamType implements ConfigType {
    private final int id;
    ScriptVarType type;
    int defaultint;
    String defaultstr;
    boolean autodisable = true;

    public ParamType(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public boolean isString() {
        return defaultstr != null;
    }

    public String getDefaultString() {
        return defaultstr;
    }

    public int getDefaultInt() {
        return defaultint;
    }

    public ScriptVarType getVarType() {
        return type;
    }
}
