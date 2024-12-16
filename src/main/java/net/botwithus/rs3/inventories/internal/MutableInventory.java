package net.botwithus.rs3.inventories.internal;

import net.botwithus.rs3.inventories.Inventory;
import net.botwithus.rs3.item.internal.MutableInvItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

public final class MutableInventory extends Inventory {

    private static final Logger log = Logger.getLogger(MutableInventory.class.getName());

    public MutableInventory(int id) {
        super(id);
    }

    public void setItem(int slot, MutableInvItem invItem) {
        int capacity = items.length;
        if (slot < 0 || slot >= capacity) {
            return;
        }
        items[slot] = invItem;
    }

    @Override
    public MutableInvItem getItem(int slot) {
        int capacity = items.length;
        if (slot < 0 || slot >= capacity) {
            return null;
        }
        return items[slot];
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void clear() {
        for (int i = 0; i < this.type.getCapacity(); i++) {
            MutableInvItem item = this.items[i];
            if (item != null) {
                item.setId(-1);
                item.setAmount(0);
            }
        }
    }

    public void setVarValue(int slot, int varId, int value) {
        if (slot < 0 || slot >= this.type.getCapacity()) {
            return;
        }
        MutableInvItem item = items[slot];
        if (item != null && item.getId() != -1) {
            if (this.domains[slot] == null) {
                this.domains[slot] = new HashMap<>();
            }
            this.domains[slot].put(varId, value);
        } else if(this.domains[slot] != null && this.domains[slot].containsKey(varId)) {
            this.domains[slot].clear();
        }
    }
}
