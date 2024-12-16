package net.botwithus.rs3.login.internal;

import net.botwithus.rs3.login.GameWorld;

public final class MutableGameWorld extends GameWorld {

    public void setWorldId(int worldId) {
        this.worldId = worldId;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setProperties(int properties) {
        this.properties = properties;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

}
