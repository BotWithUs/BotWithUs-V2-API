package net.botwithus.rs3.interfaces;

import net.botwithus.rs3.interfaces.internal.MutableInterfaceManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public sealed abstract class InterfaceManager permits MutableInterfaceManager {

    public static boolean isOpen(int interfaceId) {
        if(MutableInterfaceManager.INTERFACES.isEmpty()) {
            return false;
        }
        Interface inter = MutableInterfaceManager.INTERFACES.get(interfaceId);
        return inter != null && inter.isOpen();
    }

    public static Interface getInterface(int interfaceId) {
        return MutableInterfaceManager.INTERFACES.get(interfaceId);
    }

    public static Collection<Interface> getInterfaces() {
        return new ArrayList<>(MutableInterfaceManager.INTERFACES.values().stream().sorted(Comparator.comparingInt(Interface::getInterfaceId)).toList());
    }

    public static boolean hasTarget() {
        return MutableInterfaceManager.TARGET != null;
    }

    public static Component getTarget() {
        return MutableInterfaceManager.TARGET;
    }

}
