package net.botwithus.rs3.stats;

import net.botwithus.events.Event;

public class StatEvent implements Event {

    private final Stats stats;
    private final int gainedExperience;

    public StatEvent(Stats stats, int gainedExperience) {
        this.stats = stats;
        this.gainedExperience = gainedExperience;
    }

    public Stats getStats() {
        return stats;
    }

    public int getGainedExperience() {
        return gainedExperience;
    }
}
