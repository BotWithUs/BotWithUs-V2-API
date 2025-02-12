package net.botwithus.rs3.item;

import net.botwithus.rs3.cache.assets.ConfigManager;
import net.botwithus.rs3.cache.assets.items.ItemDefinition;
import net.botwithus.rs3.cache.assets.items.StackType;
import net.botwithus.rs3.interfaces.Component;
import net.botwithus.rs3.item.internal.MutableItem;
import net.botwithus.rs3.minimenu.Interactive;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public sealed abstract class Item implements Interactive permits InventoryItem, MutableItem {

    private static final Logger log = Logger.getLogger(Item.class.getName());

    protected int id;
    protected int quantity;

    private Component component;

    public Item(int id, int amount, Component component) {
        this.id = id;
        this.quantity = amount;
        this.component = component;
    }

    public Item(int id, int amount) {
        this(id, amount, null);
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

    public Component getComponent() {
        // Lazy-load to prevent the extra overhead from interfaces by default
        if (component == null) {
            // Impl to be determined
        }
        return component;
    }

    @Override
    public final List<String> getOptions() {
        Component component = getComponent();
        return component == null ? Collections.emptyList() : component.getOptions();
    }

    @Override
    public boolean interact(Predicate<String> predicate) {
        Component component = getComponent();
        if (component == null) {
            throw new UnsupportedOperationException("Interacting with this item is not supported");
        }
        return component.interact(predicate);
    }
}