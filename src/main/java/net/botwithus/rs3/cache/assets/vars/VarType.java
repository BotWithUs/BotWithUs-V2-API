package net.botwithus.rs3.cache.assets.vars;

import net.botwithus.rs3.cache.assets.Definition;
import net.botwithus.rs3.cache.assets.cs2.ScriptVarType;

public class VarType implements Definition {

    int domainType;
    int varId;
    ScriptVarType type;

    public VarType(int varId) {
        this.varId = varId;
    }

    public int getDomainType() {
        return domainType;
    }

    public int getVarId() {
        return varId;
    }

    public ScriptVarType getType() {
        return type;
    }

    @Override
    public int getId() {
        return varId;
    }
}
