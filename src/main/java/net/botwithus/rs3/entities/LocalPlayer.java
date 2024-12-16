package net.botwithus.rs3.entities;

import net.botwithus.rs3.vars.VarDomain;
import net.botwithus.rs3.world.Area;
import net.botwithus.rs3.world.Coordinate;
import net.botwithus.rs3.world.Direction;
import net.botwithus.rs3.world.Locatable;
import net.botwithus.services.ServiceProvider;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class LocalPlayer extends PathingEntity {

    protected LocalPlayer() {
        super(EntityType.PLAYER_ENTITY);
    }

    public abstract String getDisplayName();

    public abstract boolean isMember();

    public abstract int getServerIndex();

    public abstract int getTargetServerIndex();

    public abstract EntityType getTargetType();

    public abstract PathingEntity getPlayer();

    private static LocalPlayer self;

    public static LocalPlayer self() {
        if(self != null) {
            return self;
        }
        Optional<LocalPlayer> provide = ServiceProvider.provide(LocalPlayer.class);
        if (provide.isEmpty()) {
            return null;
        }
        self = provide.get();
        return self;
    }

    @Override
    public int getIndex() {
        return getServerIndex();
    }

    @Override
    public String getName() {
        return getDisplayName();
    }

    @Override
    public String getOverheadText() {
        var player = getPlayer();
        return player != null ? player.getOverheadText() : null;
    }

    @Override
    public Coordinate getCoordinate() {
        var player = getPlayer();
        return player != null ? player.getCoordinate() : null;
    }

    @Override
    public List<SpotAnimation> getSpotAnimations() {
        var player = getPlayer();
        return player != null ? player.getSpotAnimations() : Collections.emptyList();
    }

    @Override
    public boolean isActive() {
        var player = getPlayer();
        return player != null && player.isActive();
    }

    @Override
    public EntityType getType() {
        var player = getPlayer();
        return player != null ? player.getType() : null;
    }

    @Override
    public Direction getDirection() {
        var player = getPlayer();
        return player != null ? player.getDirection() : null;
    }

    @Override
    public Area getArea() {
        var player = getPlayer();
        return player != null ? player.getArea() : null;
    }

    @Override
    public double distanceTo(Locatable target) {
        var player = getPlayer();
        return player != null ? player.distanceTo(target) : -1;
    }

    @Override
    public boolean isMoving() {
        var player = getPlayer();
        return player != null && player.isMoving();
    }

    @Override
    public List<String> getOptions() {
        var player = getPlayer();
        return player != null ? player.getOptions() : null;
    }

    @Override
    public Headbar getHeadbar(int id) {
        var player = getPlayer();
        return player != null ? player.getHeadbar(id) : null;
    }

    @Override
    public Collection<Headbar> getHeadbars() {
        var player = getPlayer();
        return player != null ? player.getHeadbars() : null;
    }

    @Override
    public Hitmark getHitmark(int id) {
        var player = getPlayer();
        return player != null ? player.getHitmark(id) : null;
    }

    @Override
    public Collection<Hitmark> getHitmarks() {
        var player = getPlayer();
        return player != null ? player.getHitmarks() : null;
    }

    @Override
    public int getAnimationId() {
        var player = getPlayer();
        return player != null ? player.getAnimationId() : -1;
    }

    @Override
    public int getStanceId() {
        var player = getPlayer();
        return player != null ? player.getStanceId() : -1;
    }

    @Override
    public int getHealth() {
        return VarDomain.getVarBitValue(1668);
    }

    @Override
    public int getMaxHealth() {
        return VarDomain.getVarBitValue(24595);
    }

    @Override
    public int getFollowingIndex() {
        var player = getPlayer();
        return player != null ? player.getFollowingIndex() : -1;
    }

    @Override
    public EntityType getFollowingType() {
        var player = getPlayer();
        return player != null ? player.getFollowingType() : null;
    }

    @Override
    public int getTypeId() {
        return -1;
    }
}
