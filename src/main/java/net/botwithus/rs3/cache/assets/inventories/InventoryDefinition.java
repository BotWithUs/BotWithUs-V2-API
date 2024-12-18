package net.botwithus.rs3.cache.assets.inventories;

import net.botwithus.rs3.cache.assets.Definition;

public class InventoryDefinition implements Definition {

    int id;
    int capacity;
    int[] itemStock;
    int[] itemStockAmount;

    public InventoryDefinition(int id) {
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
