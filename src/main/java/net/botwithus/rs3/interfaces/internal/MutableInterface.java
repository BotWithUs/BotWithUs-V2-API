package net.botwithus.rs3.interfaces.internal;

import net.botwithus.rs3.interfaces.Interface;

import java.util.HashMap;

public final class MutableInterface extends Interface {

    public MutableInterface(int interfaceId) {
        this.interfaceId = interfaceId;
        this.components = new HashMap<>();
        this.isOpen = false;
    }

    public void addComponent(MutableComponent component) {
        component.setRoot(this);
        components.put(component.hashCode(), component);
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public MutableComponent getComponent(int uuid) {
        return (MutableComponent) components.get(uuid);
    }
}
