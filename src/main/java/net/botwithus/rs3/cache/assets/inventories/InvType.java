package net.botwithus.rs3.cache.assets.inventories;

import net.botwithus.rs3.cache.assets.ConfigType;

public class InvType implements ConfigType {

    int id;
    int capacity;
    int[] itemStock;
    int[] itemStockAmount;

    public InvType(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int[] getItemStock() {
        return itemStock;
    }

    public int[] getItemStockAmount() {
        return itemStockAmount;
    }
}
