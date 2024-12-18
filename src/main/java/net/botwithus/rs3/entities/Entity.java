package net.botwithus.rs3.entities;

import net.botwithus.rs3.world.Direction;
import net.botwithus.rs3.world.Coordinate;
import net.botwithus.rs3.world.Locatable;
import net.botwithus.rs3.world.Rotation;

public abstract class Entity implements Locatable {

    protected EntityType type;
    protected Coordinate coordinate;

    protected Direction direction;

    protected boolean isValid;

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

    public Rotation getRotation() {
        return direction.getRotation();
    }

    public boolean isValid() {
        return isValid;
    }

}
