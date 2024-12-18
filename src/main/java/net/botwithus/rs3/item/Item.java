package net.botwithus.rs3.item;

import net.botwithus.rs3.cache.assets.ConfigManager;
import net.botwithus.rs3.cache.assets.items.ItemDefinition;
import net.botwithus.rs3.cache.assets.items.StackType;
import net.botwithus.rs3.item.internal.MutableItem;

import java.util.logging.Logger;

public sealed abstract class Item permits InventoryItem, MutableItem {

    private static final Logger log = Logger.getLogger(Item.class.getName());

    protected int id;
    protected int quantity;

    public Item(int id, int amount) {
        this.id = id;
        this.quantity = amount;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemDefinition getType() {
        return ConfigManager.getItemProvider().provide(id);
    }

    public int getCategory() {
        if(id == -1) {
            return -1;
        }
        ItemDefinition type = getType();
        if(type == null) {
            return -1;
        }
        return type.getCategory();
    }

    public String getName() {
        if(id == -1) {
            return "";
        }
        ItemDefinition type = getType();
        if(type == null) {
            return "";
        }
        return type.getName();
    }

    public StackType getStackType() {
        if(id == -1) {
            return StackType.NEVER;
        }
        ItemDefinition type = getType();
        if(type == null) {
            return StackType.NEVER;
        }
        return type.getStackability();
    }
}
