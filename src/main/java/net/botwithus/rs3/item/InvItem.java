package net.botwithus.rs3.item;

import net.botwithus.rs3.inventories.Inventory;
import net.botwithus.rs3.item.Item;
import net.botwithus.rs3.item.internal.MutableInvItem;

public sealed abstract class InvItem extends Item permits MutableInvItem {

    private final Inventory inventory;
    protected int slot;

    public InvItem(int id, int amount, int slot, Inventory inventory) {
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
