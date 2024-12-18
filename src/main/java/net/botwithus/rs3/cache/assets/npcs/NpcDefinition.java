package net.botwithus.rs3.cache.assets.npcs;

import net.botwithus.rs3.cache.assets.Definition;

import java.util.Map;

public class NpcDefinition implements Definition {
    Map<Integer, Object> params;
    int[] modelIds;
    String name;
    int size;
    String[] options;
    short[] originalColors;
    short[] modifiedColors;
    short[] originalTextures;
    short[] modifiedTextures;
    byte[] recolourPalette;
    int[] headModels;
    boolean drawMapdot;
    int combatLevel;
    int resizeX;
    int resizeY;
    boolean aBool4904;
    byte ambient;
    byte contrast;
    short headIcons;
    int rotation;
    int varbit;
    int varp;
    int[] transformTo;
    boolean visible;
    boolean isClickable;
    boolean animateIdle;
    short aShort4874;
    short aShort4897;
    byte aByte4883;
    byte aByte4899;
    byte walkMask;
    int[][] modelTranslation;
    int height;
    byte respawnDirection;
    int basId;
    int movementType;
    int walkingAnimation;
    int rotate180Animation;
    int rotate90RightAnimation;
    int rotate90LeftAnimation;
    int specialByte;
    int anInt4875;
    int anInt4873;
    int anInt4854;
    int anInt4861;
    int attackOpCursor;
    int armyIcon;
    int anInt4909;
    boolean aBool4884;
    int mapIcon;
    boolean aBool4890;
    byte aByte4868;
    byte aByte4869;
    byte aByte4905;
    byte aByte4871;
    byte aByte4916;
    int[] quests;
    boolean aBool4872;
    int anInt4917;
    int anInt4911;
    int anInt4919;
    int anInt4913;
    int anInt4908;
    boolean aBool4920;
    int[] actionCursors;
    int id;

    public NpcDefinition(int id) {
        this.id = id;
        this.modelIds = new int[0];
        this.name = "";
        this.size = 1;
        this.options = new String[]{null, null, null, null, "Examine"};
        this.originalColors = new short[0];
        this.modifiedColors = new short[0];
        this.originalTextures = new short[0];
        this.modifiedTextures = new short[0];
        this.recolourPalette = new byte[0];
        this.headModels = new int[0];
        this.drawMapdot = false;
        this.combatLevel = 0;
        this.resizeX = 0;
        this.resizeY = 0;
        this.aBool4904 = false;
        this.ambient = 0;
        this.contrast = 0;
        this.headIcons = 0;
        this.rotation = 0;
        this.varbit = -1;
        this.varp = -1;
        this.transformTo = new int[0];
        this.visible = true;
        this.isClickable = true;
        this.animateIdle = true;
        this.aShort4874 = 0;
        this.aShort4897 = 0;
        this.aByte4883 = 0;
        this.aByte4899 = 0;
        this.walkMask = 0;
        this.modelTranslation = new int[0][];
        this.height = 0;
        this.params = null;
        this.respawnDirection = 0;
        this.basId = 0;
        this.movementType = 0;
        this.walkingAnimation = 0;
        this.rotate180Animation = 0;
        this.rotate90RightAnimation = 0;
        this.rotate90LeftAnimation = 0;
        this.specialByte = 0;
        this.anInt4875 = 0;
        this.anInt4873 = 0;
        this.anInt4854 = 0;
        this.anInt4861 = 0;
        this.attackOpCursor = 0;
        this.armyIcon = 0;
        this.anInt4909 = 0;
        this.aBool4884 = false;
        this.mapIcon = 0;
        this.aBool4890 = false;
        this.aByte4868 = 0;
        this.aByte4869 = 0;
        this.aByte4905 = 0;
        this.aByte4871 = 0;
        this.aByte4916 = 0;
        this.quests = new int[0];
        this.aBool4872 = false;
        this.anInt4917 = 0;
        this.anInt4911 = 0;
        this.anInt4919 = 0;
        this.anInt4913 = 0;
        this.anInt4908 = 0;
        this.aBool4920 = true;
        this.actionCursors = null;
    }

    public Map<Integer, Object> getParams() {
        return params;
    }

    public int[] getModelIds() {
        return modelIds;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String[] getOptions() {
        return options;
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

    public byte[] getRecolourPalette() {
        return recolourPalette;
    }

    public int[] getHeadModels() {
        return headModels;
    }

    public boolean isDrawMapdot() {
        return drawMapdot;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public int getResizeX() {
        return resizeX;
    }

    public int getResizeY() {
        return resizeY;
    }

    public boolean isaBool4904() {
        return aBool4904;
    }

    public byte getAmbient() {
        return ambient;
    }

    public byte getContrast() {
        return contrast;
    }

    public short getHeadIcons() {
        return headIcons;
    }

    public int getRotation() {
        return rotation;
    }

    public int getVarbit() {
        return varbit;
    }

    public int getVarp() {
        return varp;
    }

    public int[] getTransformTo() {
        return transformTo;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isClickable() {
        return isClickable;
    }

    public boolean isAnimateIdle() {
        return animateIdle;
    }

    public short getaShort4874() {
        return aShort4874;
    }

    public short getaShort4897() {
        return aShort4897;
    }

    public byte getaByte4883() {
        return aByte4883;
    }

    public byte getaByte4899() {
        return aByte4899;
    }

    public byte getWalkMask() {
        return walkMask;
    }

    public int[][] getModelTranslation() {
        return modelTranslation;
    }

    public int getHeight() {
        return height;
    }

    public byte getRespawnDirection() {
        return respawnDirection;
    }

    public int getBasId() {
        return basId;
    }

    public int getMovementType() {
        return movementType;
    }

    public int getWalkingAnimation() {
        return walkingAnimation;
    }

    public int getRotate180Animation() {
        return rotate180Animation;
    }

    public int getRotate90RightAnimation() {
        return rotate90RightAnimation;
    }

    public int getRotate90LeftAnimation() {
        return rotate90LeftAnimation;
    }

    public int getSpecialByte() {
        return specialByte;
    }

    public int getAnInt4875() {
        return anInt4875;
    }

    public int getAnInt4873() {
        return anInt4873;
    }

    public int getAnInt4854() {
        return anInt4854;
    }

    public int getAnInt4861() {
        return anInt4861;
    }

    public int getAttackOpCursor() {
        return attackOpCursor;
    }

    public int getArmyIcon() {
        return armyIcon;
    }

    public int getAnInt4909() {
        return anInt4909;
    }

    public boolean isaBool4884() {
        return aBool4884;
    }

    public int getMapIcon() {
        return mapIcon;
    }

    public boolean isaBool4890() {
        return aBool4890;
    }

    public byte getaByte4868() {
        return aByte4868;
    }

    public byte getaByte4869() {
        return aByte4869;
    }

    public byte getaByte4905() {
        return aByte4905;
    }

    public byte getaByte4871() {
        return aByte4871;
    }

    public byte getaByte4916() {
        return aByte4916;
    }

    public int[] getQuests() {
        return quests;
    }

    public boolean isaBool4872() {
        return aBool4872;
    }

    public int getAnInt4917() {
        return anInt4917;
    }

    public int getAnInt4911() {
        return anInt4911;
    }

    public int getAnInt4919() {
        return anInt4919;
    }

    public int getAnInt4913() {
        return anInt4913;
    }

    public int getAnInt4908() {
        return anInt4908;
    }

    public boolean isaBool4920() {
        return aBool4920;
    }

    public int[] getActionCursors() {
        return actionCursors;
    }

    @Override
    public int getId() {
        return id;
    }
}
