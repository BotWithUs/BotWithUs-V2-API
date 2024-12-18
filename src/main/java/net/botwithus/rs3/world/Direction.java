package net.botwithus.rs3.world;

public record Direction(float dirX, float dirY, float dirZ) {
    public boolean facing(Coordinate position, Coordinate position1) {
        // Vector from position to position1
        float segmentX = position1.x() - position.x();
        float segmentY = position1.y() - position.y();
        float segmentZ = position1.z() - position.z();

        // Normalize the direction vector
        float dirMagnitude = (float) Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ);
        float normDirX = dirX / dirMagnitude;
        float normDirY = dirY / dirMagnitude;
        float normDirZ = dirZ / dirMagnitude;

        // Normalize the segment vector
        float segmentMagnitude = (float) Math.sqrt(segmentX * segmentX + segmentY * segmentY + segmentZ * segmentZ);
        float normSegmentX = segmentX / segmentMagnitude;
        float normSegmentY = segmentY / segmentMagnitude;
        float normSegmentZ = segmentZ / segmentMagnitude;

        // Calculate the dot product of the normalized vectors
        float dotProduct = normDirX * normSegmentX + normDirY * normSegmentY + normDirZ * normSegmentZ;

        // Check if the dot product is positive, indicating the vectors are aligned
        return dotProduct > 0;
    }

    public Rotation getRotation() {
        // Calculate yaw (rotation around Y-axis)
        float yaw = (float) Math.toDegrees(Math.atan2(dirZ, dirX)) - 90;

        // Calculate pitch (rotation around X-axis)
        float pitch = (float) Math.toDegrees(Math.atan2(dirY, Math.sqrt(dirX * dirX + dirZ * dirZ)));

        // Return rotation angles
        return new Rotation(pitch, yaw);
    }
}
