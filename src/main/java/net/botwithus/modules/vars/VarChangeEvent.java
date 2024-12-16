package net.botwithus.modules.vars;

import net.botwithus.events.Event;

public record VarChangeEvent(int varId, int oldValue, int value, boolean isVarbit) implements Event {

}
