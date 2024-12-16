package net.botwithus.rs3.inventories.events;

import net.botwithus.events.Event;
import net.botwithus.rs3.inventories.Inventory;
import net.botwithus.rs3.item.InvItem;

public record InventoryEvent(InvItem oldItem, InvItem newItem, Inventory inventory) implements Event {

}
