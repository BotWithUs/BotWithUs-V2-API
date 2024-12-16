package net.botwithus.rs3.interfaces;

import net.botwithus.rs3.interfaces.internal.MutableInterface;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public sealed abstract class Interface implements Iterable<Component> permits MutableInterface {

    protected int interfaceId;

    protected Map<Integer, Component> components;

    protected boolean isOpen;

    public int getInterfaceId() {
        return interfaceId;
    }

    public List<Component> getComponents() {
        return new ArrayList<>(components.values().stream().sorted(Comparator.comparingInt(Component::getComponentId)).toList());
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Component getComponent(int uuid) {
        return components.get(uuid);
    }

    @Override
    public final Iterator<Component> iterator() {
        return new InterfaceIterator(this);
    }

    public Stream<Component> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
