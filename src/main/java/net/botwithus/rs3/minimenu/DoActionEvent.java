package net.botwithus.rs3.minimenu;

import net.botwithus.events.Event;
import net.botwithus.rs3.entities.EntityType;

public record DoActionEvent(Action action, int param1, int param2, int param3, int itemId, EntityType type, int entityId, int x, int y, int plane) implements Event {
}
