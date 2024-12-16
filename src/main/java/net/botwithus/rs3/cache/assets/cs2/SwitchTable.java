package net.botwithus.rs3.cache.assets.cs2;

public class SwitchTable {

    private int cases_count;

    private Instruction[] jumps;

    public SwitchTable(int cases_count) {
        this.cases_count = cases_count;
        this.jumps = new Instruction[cases_count];
    }

    public int getCaseCount() {
        return cases_count;
    }

    public Instruction[] getJumps() {
        return jumps;
    }

    public void setJump(int key, Instruction jump) {
        if (key >= 0 && key < cases_count) {
            jumps[key] = jump;
        }
    }
}
