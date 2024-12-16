package net.botwithus.rs3.world;


public interface Locatable {
    /**
     * Returns the position of the entity on the world graph.
     *
     * @return The position of the entity, or null if it cannot be determined or is no longer on the world graph.
     */
    default Coordinate getCoordinate() {
        Area coordinate = getArea();
        if (coordinate == null) {
            return null;
        }
        return coordinate.getCoordinate();
    }

    /**
     * Gets the area that the entity occupies on the world graph
     *
     * @return The area of the entity, or null if it cannot be determined or is no longer on the world graph.
     */
    Area getArea();

    default double distanceTo(Locatable target) {
        return Distance.between(this, target);
    }


}
