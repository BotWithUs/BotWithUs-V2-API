package net.botwithus.rs3.item;

import net.botwithus.rs3.inventories.Inventory;
import net.botwithus.rs3.item.internal.MutableInventoryItem;

public sealed abstract class InventoryItem extends Item permits MutableInventoryItem {

    private final Inventory inventory;
    protected int slot;

    public InventoryItem(int id, int amount, int slot, Inventory inventory) {
        super(id, amount);
        this.inventory = inventory;
        this.slot = slot;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getSlot() {
        return slot;
    }
}
