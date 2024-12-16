package net.botwithus.rs3.inventories;

import net.botwithus.rs3.inventories.internal.InventoryHandler;

import java.util.ArrayList;
import java.util.List;

public final class InventoryManager {

    public static Inventory getInventory(int id) {
        return InventoryHandler.INVENTORIES.get(id);
    }

    public static List<Inventory> getInventories() {
        return new ArrayList<>(InventoryHandler.INVENTORIES.values());
    }
}
