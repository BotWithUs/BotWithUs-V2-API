package net.botwithus.rs3.stats.internal;

import net.botwithus.rs3.stats.Stat;

public final class MutableStat extends Stat {

    public MutableStat(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setElite(boolean isElite) {
        this.isElite = isElite;
    }

    public void setMembers(boolean isMembers) {
        this.isMembers = isMembers;
    }
}
