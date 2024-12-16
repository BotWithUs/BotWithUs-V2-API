package net.botwithus.rs3.cache.assets.locs;

import net.botwithus.rs3.cache.assets.ConfigType;

import java.util.concurrent.ConcurrentHashMap;

public final class LocType implements ConfigType {
    int id;
    int[][] modelIds;
    int[] types;
    String name;
    int solidType;
    int interactable;
    byte groundContoured;
    boolean delayShading;
    int occludes;
    int[] animations;
    int decorDisplacement;
    byte ambient;
    byte contrast;
    String[] options = new String[6];
    int sizeX;
    int sizeY;
    short[] originalColors;
    short[] modifiedColors;
    short[] originalTextures;
    short[] modifiedTextures;
    boolean inverted;
    boolean castsShadow;
    short scaleX;
    short scaleY;
    short scaleZ;
    int accessBlockFlag;
    int offsetX;
    int offsetY;
    int offsetZ;
    boolean obstructsGround;
    boolean breakroutefinding;
    int supportsItems;
    int varbit = -1;
    int varp = -1;
    int[] immitations;
    int ambientSoundHearDistance;
    short ambientSoundId;
    int[] audioTracks;
    boolean hidden;
    boolean members;
    boolean adjustMapSceneRotation;
    boolean hasAnimation;
    int mapSpriteRotation;
    int mapSpriteId;
    int ambientSoundVolume;
    boolean flipMapSprite;
    int[] animProbs;
    short mapIcon;
    boolean mapFunction;
    int type;
    boolean blocksProjectile;
    boolean blocksGround;
    boolean aBoolean5720;
    int[] actionCursors;
    int[] unknownFields;
    ConcurrentHashMap<Integer, Object> params;

    public LocType(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public int[][] getModelIds() {
        return modelIds;
    }

    public int[] getTypes() {
        return types;
    }

    public String getName() {
        return name;
    }

    public int getSolidType() {
        return solidType;
    }

    public int getInteractable() {
        return interactable;
    }

    public byte getGroundContoured() {
        return groundContoured;
    }

    public boolean isDelayShading() {
        return delayShading;
    }

    public int getOccludes() {
        return occludes;
    }

    public int[] getAnimations() {
        return animations;
    }

    public int getDecorDisplacement() {
        return decorDisplacement;
    }

    public byte getAmbient() {
        return ambient;
    }

    public byte getContrast() {
        return contrast;
    }

    public String[] getOptions() {
        return options;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public short[] getOriginalColors() {
        return originalColors;
    }

    public short[] getModifiedColors() {
        return modifiedColors;
    }

    public short[] getOriginalTextures() {
        return originalTextures;
    }

    public short[] getModifiedTextures() {
        return modifiedTextures;
    }

    public boolean isInverted() {
        return inverted;
    }

    public boolean isCastsShadow() {
        return castsShadow;
    }

    public short getScaleX() {
        return scaleX;
    }

    public short getScaleY() {
        return scaleY;
    }

    public short getScaleZ() {
        return scaleZ;
    }

    public int getAccessBlockFlag() {
        return accessBlockFlag;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getOffsetZ() {
        return offsetZ;
    }

    public boolean isObstructsGround() {
        return obstructsGround;
    }

    public boolean isBreakroutefinding() {
        return breakroutefinding;
    }

    public int getSupportsItems() {
        return supportsItems;
    }

    public int getVarbit() {
        return varbit;
    }

    public int getVarp() {
        return varp;
    }

    public int[] getImmitations() {
        return immitations;
    }

    public int getAmbientSoundHearDistance() {
        return ambientSoundHearDistance;
    }

    public short getAmbientSoundId() {
        return ambientSoundId;
    }

    public int[] getAudioTracks() {
        return audioTracks;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isMembers() {
        return members;
    }

    public boolean isAdjustMapSceneRotation() {
        return adjustMapSceneRotation;
    }

    public boolean isHasAnimation() {
        return hasAnimation;
    }

    public int getMapSpriteRotation() {
        return mapSpriteRotation;
    }

    public int getMapSpriteId() {
        return mapSpriteId;
    }

    public int getAmbientSoundVolume() {
        return ambientSoundVolume;
    }

    public boolean isFlipMapSprite() {
        return flipMapSprite;
    }

    public int[] getAnimProbs() {
        return animProbs;
    }

    public short getMapIcon() {
        return mapIcon;
    }

    public boolean isMapFunction() {
        return mapFunction;
    }

    public int getType() {
        return type;
    }

    public boolean isBlocksProjectile() {
        return blocksProjectile;
    }

    public boolean isBlocksGround() {
        return blocksGround;
    }

    public boolean isaBoolean5720() {
        return aBoolean5720;
    }

    public int[] getActionCursors() {
        return actionCursors;
    }

    public int[] getUnknownFields() {
        return unknownFields;
    }

    public ConcurrentHashMap<Integer, Object> getParams() {
        return params;
    }
}
