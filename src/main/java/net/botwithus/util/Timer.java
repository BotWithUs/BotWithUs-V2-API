package net.botwithus.util;

public class Timer {
    private long startTime;

    public Timer() {
        reset();
    }

    // Resets the timer to the current time
    public void reset() {
        startTime = System.currentTimeMillis();
    }

    // Checks if the specified time (in milliseconds) has elapsed
    public boolean elapsed(long milliseconds) {
        return (System.currentTimeMillis() - startTime) >= milliseconds;
    }
}
