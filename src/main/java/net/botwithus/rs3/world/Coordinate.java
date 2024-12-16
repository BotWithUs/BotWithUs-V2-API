package net.botwithus.rs3.world;

public record Coordinate(int x, int y, int z) implements Locatable {

    @Override
    public Area getArea() {
        return new Area.Singular(this);
    }

    public int getRegionX() {
        return (x >> 6);
    }

    public int getRegionY() {
        return (y >> 6);
    }

    public int getXInRegion() {
        return x & 0x3F;
    }

    public int getYInRegion() {
        return y & 0x3F;
    }

    public boolean isWestOf(Locatable loc) {
        return this.x < loc.getCoordinate().x;
    }

    public boolean isEastOf(Locatable loc) {
        return !isWestOf(loc);
    }

    public boolean isNorthOf(Locatable loc) {
        return this.y > loc.getCoordinate().y;
    }

    public boolean isSouthOf(Locatable loc) {
        return !isNorthOf(loc);
    }

    public Coordinate derive(int xOffset, int yOffset, int planeOffset) {
        if (xOffset == 0 && yOffset == 0 && planeOffset == 0) {
            return this;
        }
        return new Coordinate(this.x + xOffset, this.y + yOffset, this.z + planeOffset);
    }

}
