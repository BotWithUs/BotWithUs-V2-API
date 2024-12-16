package net.botwithus.rs3.cache.assets.items;

import net.botwithus.rs3.cache.assets.ConfigType;
import net.botwithus.rs3.cache.assets.params.ParamType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ItemType implements ConfigType {
    int modelId = 0;
    String name = "";
    int modelZoom = 2000;
    int modelRotationX = 0;
    int modelRotationY = 0;
    int modelOffsetX = 0;
    int modelOffsetY = 0;
    byte stackable = 0;
    long price = 1;
    byte wearpos = -1;
    byte wearpos2 = -1;
    boolean op15Bool = false;
    boolean members = false;
    int multistackSize = 0;
    int maleModel1 = -1;
    int maleModel2 = -1;
    int femaleModel1 = -1;
    int femaleModel2 = -1;
    byte wearpos3 = -1;
    String[] groundActions = new String[5];
    String[] inventoryActions = new String[5];
    short[] originalColors = new short[0];
    short[] replacementColors = new short[0];
    short[] originalTextures = new short[0];
    short[] replacementTextures = new short[0];
    byte[] recolorPalette = new byte[0];
    boolean exchangeable = false;
    int maleModel3 = -1;
    int femaleModel3 = -1;
    int maleHeadModel1 = -1;
    int femaleHeadModel1 = -1;
    int maleHeadModel2 = -1;
    int femaleHeadModel2 = -1;
    int modelAngleZ = 0;
    int searchable = 0;
    int notedItemId = -1;
    int notedTemplate = -1;
    int[] stackAmounts = null;
    int[] stackIds = null;
    int resizeX = 0;
    int resizeY = 0;
    int resizeZ = 0;
    byte ambient = 0;
    int contrast = 0;
    byte teamId = 0;
    int lentItemId = -1;
    int lendTemplate = -1;
    int maleModelOffsetX = 0;
    int maleModelOffsetY = 0;
    int maleModelOffsetZ = 0;
    int femaleModelOffsetX = 0;
    int femaleModelOffsetY = 0;
    int femaleModelOffsetZ = 0;
    int[] questIds = new int[0];
    int pickSizeShift = 0;
    int bindId = -1;
    int boundTemplate = -1;
    int[] groundCursors = null;
    int[] inventoryCursors = null;
    int shardItemId = -1;
    int shardTemplateId = -1;
    String buffEffect = "";
    int geBuyLimit = 0;
    int category = 0;
    boolean randomizeGroundPos = false;
    short shardCombineAmount = 0;
    String shardName = "";
    boolean neverStackable = false;
    boolean noted = false;
    boolean lended = false;
    int id;
    Map<Integer, Object> params;

    public ItemType(int id) {
        this.id = id;
    }

    public void toNote(ItemType note) {
        if (note != null) {
            this.members = note.members;
            this.price = note.price;
            this.name = note.name;
            this.stackable = 1;
            this.noted = true;
            this.params = note.params;
            this.category = note.category;
        }
    }

    public List<String> getGroundOptions() {
        return Arrays.stream(groundActions).toList();
    }

    public List<String> getBackpackOptions() {
        return Arrays.stream(inventoryActions).toList();
    }

    public long getCost() {
        return price;
    }

    public StackType getStackability() {
        return stackable == 1 ? StackType.ALWAYS : StackType.NEVER;
    }

    public int getEquipmentSlotId() {
        return wearpos;
    }

    public int getGrandExchangeBuyLimit() {
        return geBuyLimit;
    }

    public boolean isMembersOnly() {
        return members;
    }

    /**
     * Gets all the ids of the params that are being overwritten in this config.
     *
     * @return a non-null list of param ids.
     */

    public List<Integer> getParamIds() {
        return params != null ? params.keySet().stream().toList() : Collections.emptyList();
    }

    /**
     * @param paramId the id of the ParamType to see if this config overrides.
     */
    public boolean hasParam(int paramId) {
        return params != null && params.containsKey(paramId);
    }

    /**
     * @param paramId The id of the {@code ParamType} that this ConfigType has overridden values for.
     */
    public int getIntParam(int paramId) {
        if (hasParam(paramId)) {
            Object value = params.get(paramId);
            if (value instanceof Integer i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param paramId The id of the {@code ParamType} that this ConfigType has overridden values for.
     */
    public String getStringParam(int paramId) {
        if (hasParam(paramId)) {
            Object value = params.get(paramId);
            if (value instanceof String s) {
                return s;
            }
        }
        return null;
    }

    public boolean isNote() {
        return noted;
    }

    public int getModelId() {
        return modelId;
    }

    public String getName() {
        return name;
    }

    public int getModelZoom() {
        return modelZoom;
    }

    public int getModelRotationX() {
        return modelRotationX;
    }

    public int getModelRotationY() {
        return modelRotationY;
    }

    public int getModelOffsetX() {
        return modelOffsetX;
    }

    public int getModelOffsetY() {
        return modelOffsetY;
    }

    public byte getStackable() {
        return stackable;
    }

    public long getPrice() {
        return price;
    }

    public byte getWearpos() {
        return wearpos;
    }

    public byte getWearpos2() {
        return wearpos2;
    }

    public boolean isOp15Bool() {
        return op15Bool;
    }

    public boolean isMembers() {
        return members;
    }

    public int getMultistackSize() {
        return multistackSize;
    }

    public int getMaleModel1() {
        return maleModel1;
    }

    public int getMaleModel2() {
        return maleModel2;
    }

    public int getFemaleModel1() {
        return femaleModel1;
    }

    public int getFemaleModel2() {
        return femaleModel2;
    }

    public byte getWearpos3() {
        return wearpos3;
    }

    public String[] getGroundActions() {
        return groundActions;
    }

    public String[] getInventoryActions() {
        return inventoryActions;
    }

    public short[] getOriginalColors() {
        return originalColors;
    }

    public short[] getReplacementColors() {
        return replacementColors;
    }

    public short[] getOriginalTextures() {
        return originalTextures;
    }

    public short[] getReplacementTextures() {
        return replacementTextures;
    }

    public byte[] getRecolorPalette() {
        return recolorPalette;
    }

    public boolean isExchangeable() {
        return exchangeable;
    }

    public int getMaleModel3() {
        return maleModel3;
    }

    public int getFemaleModel3() {
        return femaleModel3;
    }

    public int getMaleHeadModel1() {
        return maleHeadModel1;
    }

    public int getFemaleHeadModel1() {
        return femaleHeadModel1;
    }

    public int getMaleHeadModel2() {
        return maleHeadModel2;
    }

    public int getFemaleHeadModel2() {
        return femaleHeadModel2;
    }

    public int getModelAngleZ() {
        return modelAngleZ;
    }

    public int getSearchable() {
        return searchable;
    }

    public int getNotedItemId() {
        return notedItemId;
    }

    public int getNotedTemplate() {
        return notedTemplate;
    }

    public int[] getStackAmounts() {
        return stackAmounts;
    }

    public int[] getStackIds() {
        return stackIds;
    }

    public int getResizeX() {
        return resizeX;
    }

    public int getResizeY() {
        return resizeY;
    }

    public int getResizeZ() {
        return resizeZ;
    }

    public byte getAmbient() {
        return ambient;
    }

    public int getContrast() {
        return contrast;
    }

    public byte getTeamId() {
        return teamId;
    }

    public int getLentItemId() {
        return lentItemId;
    }

    public int getLendTemplate() {
        return lendTemplate;
    }

    public int getMaleModelOffsetX() {
        return maleModelOffsetX;
    }

    public int getMaleModelOffsetY() {
        return maleModelOffsetY;
    }

    public int getMaleModelOffsetZ() {
        return maleModelOffsetZ;
    }

    public int getFemaleModelOffsetX() {
        return femaleModelOffsetX;
    }

    public int getFemaleModelOffsetY() {
        return femaleModelOffsetY;
    }

    public int getFemaleModelOffsetZ() {
        return femaleModelOffsetZ;
    }

    public int[] getQuestIds() {
        return questIds;
    }

    public int getPickSizeShift() {
        return pickSizeShift;
    }

    public int getBindId() {
        return bindId;
    }

    public int getBoundTemplate() {
        return boundTemplate;
    }

    public int[] getGroundCursors() {
        return groundCursors;
    }

    public int[] getInventoryCursors() {
        return inventoryCursors;
    }

    public int getShardItemId() {
        return shardItemId;
    }

    public int getShardTemplateId() {
        return shardTemplateId;
    }

    public String getBuffEffect() {
        return buffEffect;
    }

    public int getGeBuyLimit() {
        return geBuyLimit;
    }

    public int getCategory() {
        return category;
    }

    public boolean isRandomizeGroundPos() {
        return randomizeGroundPos;
    }

    public short getShardCombineAmount() {
        return shardCombineAmount;
    }

    public String getShardName() {
        return shardName;
    }

    public boolean isNeverStackable() {
        return neverStackable;
    }

    public boolean isNoted() {
        return noted;
    }

    public boolean isLended() {
        return lended;
    }

    public Map<Integer, Object> getParams() {
        return params;
    }

    public int getId() {
        return id;
    }
}
