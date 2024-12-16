package net.botwithus.rs3.entities;

import net.botwithus.rs3.world.Direction;
import net.botwithus.rs3.world.Coordinate;
import net.botwithus.rs3.world.Locatable;

public abstract class Entity implements Locatable {

    protected EntityType type;
    protected Coordinate coordinate;

    protected Direction direction;

    protected boolean isActive;

    protected Entity(EntityType type) {
        this.type = type;
    }

    public EntityType getType() {
        return type;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isActive() {
        return isActive;
    }

}
