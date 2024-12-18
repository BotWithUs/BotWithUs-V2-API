package net.botwithus.rs3.cache.assets.cs2;

import net.botwithus.rs3.cache.assets.Definition;

public class ScriptType implements Definition {

    final int scriptId;
    String name;
    int localIntCount;
    int localStringCount;
    int localLongCount;

    int intArgumentCount;
    int stringArgumentCount;
    int longArgumentCount;

    int instructionCount;

    int switchTableCount;

    SwitchTable[] switchTables;

    Instruction[] instructions;

    public ScriptType(int scriptID) {
        this.scriptId = scriptID;
    }

    @Override
    public int getId() {
        return scriptId;
    }

    public int getScriptId() {
        return scriptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocalIntCount() {
        return localIntCount;
    }

    public void setLocalIntCount(int localIntCount) {
        this.localIntCount = localIntCount;
    }

    public int getLocalStringCount() {
        return localStringCount;
    }

    public void setLocalStringCount(int localStringCount) {
        this.localStringCount = localStringCount;
    }

    public int getLocalLongCount() {
        return localLongCount;
    }

    public void setLocalLongCount(int localLongCount) {
        this.localLongCount = localLongCount;
    }

    public int getIntArgumentCount() {
        return intArgumentCount;
    }

    public void setIntArgumentCount(int intArgumentCount) {
        this.intArgumentCount = intArgumentCount;
    }

    public int getStringArgumentCount() {
        return stringArgumentCount;
    }

    public void setStringArgumentCount(int stringArgumentCount) {
        this.stringArgumentCount = stringArgumentCount;
    }

    public int getLongArgumentCount() {
        return longArgumentCount;
    }

    public void setLongArgumentCount(int longArgumentCount) {
        this.longArgumentCount = longArgumentCount;
    }

    public int getInstructionCount() {
        return instructionCount;
    }

    public void setInstructionCount(int instructionCount) {
        this.instructionCount = instructionCount;
    }

    public int getSwitchTableCount() {
        return switchTableCount;
    }

    public void setSwitchTableCount(int switchTableCount) {
        this.switchTableCount = switchTableCount;
    }

    public SwitchTable[] getSwitchTables() {
        return switchTables;
    }

    public void setSwitchTables(SwitchTable[] switchTables) {
        this.switchTables = switchTables;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }

    public void setInstructions(Instruction[] instructions) {
        this.instructions = instructions;
    }
}
