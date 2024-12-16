package net.botwithus.rs3.item.internal;

import net.botwithus.rs3.item.Item;

public final class MutableItem extends Item {
    public MutableItem(int id, int amount) {
        super(id, amount);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.quantity = amount;
    }
}
