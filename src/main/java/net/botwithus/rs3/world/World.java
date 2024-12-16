package net.botwithus.rs3.world;

import net.botwithus.rs3.entities.*;
import net.botwithus.rs3.world.internal.MutableWorld;

import java.util.ArrayList;
import java.util.Collection;

public sealed abstract class World permits MutableWorld {

    public static PathingEntity getNpc(int index) {
        return MutableWorld.NPCS.get(index);
    }

    public static PathingEntity getPlayer(int index) {
        return MutableWorld.PLAYERS.get(index);
    }

    public static Collection<PathingEntity> getPlayers() {
        return MutableWorld.PLAYERS.values();
    }

    public static Collection<PathingEntity> getNpcs() {
        return MutableWorld.NPCS.values();
    }

    public static SceneObject getSceneObject(int x, int y, int plane) {
        return MutableWorld.SCENE_OBJECTS.get((plane << 28) | (x << 14) | y);
    }

    public static Collection<SceneObject> getSceneObjects() {
        return new ArrayList<>(MutableWorld.SCENE_OBJECTS.values());
    }

    public static Collection<SpotAnimation> getSpotAnimations() {
        return new ArrayList<>(MutableWorld.SPOT_ANIMATIONS.values());
    }

    public static Collection<ItemStack> getGroundItems() {
        return new ArrayList<>(MutableWorld.ITEM_STACKS.values());
    }

    public static Collection<Projectile> getProjectiles() {
        return new ArrayList<>(MutableWorld.PROJECTILES.values());
    }
}
