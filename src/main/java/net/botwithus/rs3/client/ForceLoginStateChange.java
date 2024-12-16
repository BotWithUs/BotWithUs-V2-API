package net.botwithus.rs3.client;

public record ForceLoginStateChange(int prevState, int state) implements StateChange {
}
