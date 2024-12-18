package net.botwithus.rs3.inventories;

import net.botwithus.rs3.cache.assets.ConfigManager;
import net.botwithus.rs3.cache.assets.inventories.InvType;
import net.botwithus.rs3.cache.assets.vars.VarBitType;
import net.botwithus.rs3.cache.assets.vars.VarDomainType;
import net.botwithus.rs3.inventories.internal.MutableInventory;
import net.botwithus.rs3.item.InventoryItem;
import net.botwithus.rs3.item.Item;
import net.botwithus.rs3.item.internal.MutableInventoryItem;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public sealed class Inventory implements Iterable<InventoryItem> permits MutableInventory {

    protected final int id;
    protected final InvType type;
    protected MutableInventoryItem[] items;
    protected boolean isActive = false;
    protected Map<Integer, Integer>[] domains;

    public Inventory(int id) {
        this.id = id;
        this.type = ConfigManager.getInvProvider().provide(id);
        if (this.type != null) {
            this.domains = new HashMap[this.type.getCapacity()];
            this.items = new MutableInventoryItem[this.type.getCapacity()];
            for (int i = 0; i < this.type.getCapacity(); i++) {
                this.items[i] = new MutableInventoryItem(-1, 0, i, this);
            }
        } else {
            this.items = new MutableInventoryItem[0];
        }
    }

    public int getId() {
        return id;
    }

    public InvType getType() {
        return type;
    }

    public Item getItem(int slot) {
        return items[slot];
    }

    public List<InventoryItem> getItems() {
        return Arrays.asList(items);
    }

    public boolean isFull() {
        return Arrays.stream(items).map(InventoryItem::getId).filter(i -> i != -1).count() == type.getCapacity();
    }

    public int freeSlots() {
        return (int) Arrays.stream(items).filter(i -> i.getId() == -1).count();
    }

    public boolean contains(int id) {
        return Arrays.stream(items).anyMatch(i -> i.getId() == id);
    }

    public boolean contains(int id, int amount) {
        return Arrays.stream(items).filter(i -> i.getId() == id).mapToInt(InventoryItem::getQuantity).sum() >= amount;
    }

    public boolean containsAll(int... ids) {
        return Arrays.stream(ids).allMatch(this::contains);
    }

    public boolean containsAll(List<Integer> ids) {
        return ids.stream().allMatch(this::contains);
    }

    public boolean containsAny(int... ids) {
        return Arrays.stream(ids).anyMatch(this::contains);
    }

    public boolean containsAny(List<Integer> ids) {
        return ids.stream().anyMatch(this::contains);
    }

    public int count(int id) {
        return Arrays.stream(items).filter(i -> i.getId() == id).mapToInt(InventoryItem::getQuantity).sum();
    }

    public int countAll(int... ids) {
        return Arrays.stream(ids).map(this::count).sum();
    }

    public boolean containsByCategory(int category) {
        return Arrays.stream(items).anyMatch(i -> i.getCategory() == category);
    }

    public boolean containsByCategory(int category, int amount) {
        return Arrays.stream(items).filter(i -> i.getCategory() == category).mapToInt(InventoryItem::getQuantity).sum() >= amount;
    }

    public boolean containsAllByCategory(int... categories) {
        return Arrays.stream(categories).allMatch(this::containsByCategory);
    }

    public int getVarValue(int slot, int id) {
        Map<Integer, Integer> domain = domains[slot];
        if(domain == null) {
            return 0;
        }
        return domain.getOrDefault(id, 0);
    }

    public int getVarbitValue(int slot, int varbitId) {
        VarBitType type = ConfigManager.getVarBitProvider().provide(varbitId);
        if(type == null) {
            return 0;
        }
        if(type.getDomainType() != VarDomainType.OBJECT) {
            return 0;
        }
        int value = getVarValue(slot, type.getVarId());
        int mask = (1 << ((type.getMsb() - type.getLsb()) + 1)) - 1;
        return (value >> type.getLsb()) & mask;
    }

    public Map<Integer, Integer> getDomain(int slot) {
        return domains[slot];
    }

    public boolean hasDomains() {
        return Arrays.stream(domains).anyMatch(Objects::nonNull);
    }

    @Override
    public Iterator<InventoryItem> iterator() {
        return new Iterator<>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < items.length;
            }

            @Override
            public InventoryItem next() {
                return items[index++];
            }
        };
    }

    public Stream<InventoryItem> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }
}
