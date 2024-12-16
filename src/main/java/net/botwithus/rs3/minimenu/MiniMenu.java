package net.botwithus.rs3.minimenu;

import net.botwithus.services.ServiceProvider;

public interface MiniMenu {

    boolean doAction0(Action action, int param1, int param2, int param3);

    static boolean doAction(Action action, int param1, int param2, int param3) {
        return ServiceProvider.provide(MiniMenu.class).map(miniMenu -> miniMenu.doAction0(action, param1, param2, param3)).orElse(false);
    }

}
