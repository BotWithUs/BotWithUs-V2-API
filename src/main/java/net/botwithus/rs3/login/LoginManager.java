package net.botwithus.rs3.login;

import net.botwithus.rs3.client.ForceLoginStateChange;
import net.botwithus.rs3.client.StateWorldSwitch;
import net.botwithus.rs3.client.internal.MutableClient;
import net.botwithus.rs3.login.internal.MutableLoginManager;

import java.util.ArrayList;
import java.util.Collection;

import static net.botwithus.rs3.login.internal.MutableLoginManager.LOGIN_PROGRESS;
import static net.botwithus.rs3.login.internal.MutableLoginManager.LOGIN_STATUS;

public sealed abstract class LoginManager permits MutableLoginManager {

    public static Collection<GameWorld> getGameWorlds() {
        return new ArrayList<>(MutableLoginManager.GAME_WORLDS.values());
    }

    public static int getLoginProgress() {
        return LOGIN_PROGRESS;
    }

    public static int getLoginStatus() {
        return LOGIN_STATUS;
    }

    public static void switchWorld(GameWorld world) {
        MutableClient.STATE_CHANGES.offer(new StateWorldSwitch(world.getWorldId()));
        MutableClient.STATE_CHANGES.offer(new ForceLoginStateChange(30, 37));
    }

    public static void switchWorld(int world) {
        MutableClient.STATE_CHANGES.offer(new StateWorldSwitch(world));
        MutableClient.STATE_CHANGES.offer(new ForceLoginStateChange(30, 37));
    }

    public static void logout() {
        MutableClient.STATE_CHANGES.offer(new ForceLoginStateChange(MutableClient.getClientState().getId(), 10));
    }

    public static void forceLoginState(int prev, int state) {
        MutableClient.STATE_CHANGES.offer(new ForceLoginStateChange(prev, state));
    }
}
