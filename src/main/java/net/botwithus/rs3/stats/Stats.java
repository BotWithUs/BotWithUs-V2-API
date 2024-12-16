package net.botwithus.rs3.stats;

import net.botwithus.rs3.stats.internal.MutableStat;

public enum Stats {

    ATTACK(new MutableStat(0)),
    DEFENSE(new MutableStat(1)),
    STRENGTH(new MutableStat(2)),
    CONSTITUTION(new MutableStat(3)),
    RANGED(new MutableStat(4)),
    PRAYER(new MutableStat(5)),
    MAGIC(new MutableStat(6)),
    COOKING(new MutableStat(7)),
    WOODCUTTING(new MutableStat(8)),
    FLETCHING(new MutableStat(9)),
    FISHING(new MutableStat(10)),
    FIREMAKING(new MutableStat(11)),
    CRAFTING(new MutableStat(12)),
    SMITHING(new MutableStat(13)),
    MINING(new MutableStat(14)),
    HERBLORE(new MutableStat(15)),
    AGILITY(new MutableStat(16)),
    THIEVING(new MutableStat(17)),
    SLAYER(new MutableStat(18)),
    FARMING(new MutableStat(19)),
    RUNECRAFTING(new MutableStat(20)),
    HUNTER(new MutableStat(21)),
    CONSTRUCTION(new MutableStat(22)),
    SUMMONING(new MutableStat(23)),
    DUNGEONEERING(new MutableStat(24)),
    DIVINATION(new MutableStat(25)),
    INVENTION(new MutableStat(26)),
    ARCHAEOLOGY(new MutableStat(27)),
    NECROMANCY(new MutableStat(28));

    private MutableStat stat;

    Stats(MutableStat stat) {
        this.stat = stat;
    }

    public Stat getStat() {
        return stat;
    }

    public int getId() {
        return stat.getId();
    }

    public int getLevel() {
        return stat.getLevel();
    }

    public int getXp() {
        return stat.getXp();
    }

    public int getMaxLevel() {
        return stat.getMaxLevel();
    }

    public int getCurrentLevel() {
        return stat.getCurrentLevel();
    }

    private static final Stats[] values = values();

    public static Stats fromId(int id) {
        for (Stats value : values) {
            if (value.stat.getId() == id) {
                return value;
            }
        }
        return null;
    }
}
