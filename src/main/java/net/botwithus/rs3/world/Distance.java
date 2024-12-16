package net.botwithus.rs3.world;


import net.botwithus.rs3.entities.LocalPlayer;
import net.botwithus.rs3.world.Area;
import net.botwithus.rs3.world.Coordinate;
import net.botwithus.rs3.world.Locatable;

public class Distance {
    /**
     * Gets the distance between two Locatable entities that exist on the world graph.
     * If either Locatable is not on the world graph or they're on different Z levels, the method returns Double.NaN.
     *
     * @param first  a non-null Locatable on the world graph.
     * @param second a different non-null Locatable on the world graph.
     * @return The distance between the two Locatables, or Double.NaN if either Locatable is not on the world graph or they're on different Z levels.
     */
    public static double between(Locatable first, Locatable second) {
        Area area1 = first.getArea();
        if (area1 == null) {
            return Double.NaN;
        }
        Area area2 = second.getArea();
        if (area2 == null) {
            return Double.NaN;
        }
        Coordinate center1 = area1.getCentroid();
        if (center1 == null) {
            return Double.NaN;
        }
        Coordinate center2 = area2.getCentroid();
        if (center2 == null) {
            return Double.NaN;
        }
        if (center1.z() != center2.z()) {
            return Double.NaN;
        }
        return between(center1.x(), center1.y(), center2.x(), center2.y());
    }

    /**
     * Gets the euclidean distance between the two sets of (x,y) points.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @return The Euclidean distance between the two points.
     */
    public static double between(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    /**
     * Gets the euclidean distance between the local player and {@param other} assuming they're on the same plane/z-level.
     *
     * @param other A locatable that exists on the world graph.
     * @return The euclidean distance between the local player and {@param other} in 2d space, or Double.NaN if it can't be determined.
     */
    public static double to(Locatable other) {
        var self = LocalPlayer.self();
        if (self == null) {
            return Double.NaN;
        }
        Coordinate coord = self.getCoordinate();
        if (coord == null) {
            return Double.NaN;
        }
        Coordinate coord2 = other.getCoordinate();
        if (coord2 == null) {
            return Double.NaN;
        }
        if (coord.z() != coord2.z()) {
            return Double.NaN;
        }
        return between(self, other);
    }
}