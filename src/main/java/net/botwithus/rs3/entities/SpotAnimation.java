package net.botwithus.rs3.entities;

import net.botwithus.rs3.entities.internal.MutableSpotAnimation;

public abstract sealed class SpotAnimation extends Entity permits MutableSpotAnimation {

    protected int id;

    protected SpotAnimation(EntityType type) {
        super(type);
    }

    public int getId() {
        return id;
    }
}
