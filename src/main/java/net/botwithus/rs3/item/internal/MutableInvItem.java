package net.botwithus.rs3.item.internal;

import net.botwithus.rs3.inventories.Inventory;
import net.botwithus.rs3.item.InvItem;

public final class MutableInvItem extends InvItem {
    public MutableInvItem(int id, int amount, int slot, Inventory inventory) {
        super(id, amount, slot, inventory);
    }

    public void setAmount(int amount) {
        this.quantity = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
