package net.botwithus.rs3.cache.assets.vars;

import net.botwithus.rs3.cache.assets.Definition;

public class VarBitType implements Definition {

    int id;
    VarDomainType domainType;
    int varId;
    int lsb;
    int msb;

    public VarBitType(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public VarDomainType getDomainType() {
        return domainType;
    }

    public int getVarId() {
        return varId;
    }

    public int getLsb() {
        return lsb;
    }

    public int getMsb() {
        return msb;
    }
}
