package net.botwithus.rs3.interfaces;

import net.botwithus.rs3.cache.assets.ConfigManager;
import net.botwithus.rs3.cache.assets.ConfigProvider;
import net.botwithus.rs3.cache.assets.params.ParamType;
import net.botwithus.rs3.interfaces.internal.MutableComponent;

import java.util.*;

public sealed abstract class Component permits MutableComponent {

    protected Interface root;

    protected ComponentType type;

    protected int componentId;

    protected int subComponentId;

    protected boolean isHidden;

    protected int properties;

    protected int fontId;

    protected int color;

    protected int alpha;

    protected int itemId;

    protected int itemAmount;

    protected int spriteId;

    protected String text;

    protected String optionBase;

    protected String[] options;

    protected Map<Integer, Component> children;

    protected Map<Integer, Object> params;

    public Component(ComponentType type) {
        this.type = type;
        this.isHidden = false;
        this.text = null;
        this.optionBase = null;
        this.options = new String[10];
        this.subComponentId = -1;
        if (type == ComponentType.LAYER) {
            this.children = new HashMap<>();
        } else {
            this.children = Collections.emptyMap();
        }
        this.params = new HashMap<>();
    }

    public Interface getRoot() {
        return root;
    }

    public ComponentType getType() {
        return type;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public String getText() {
        return text;
    }

    public String getOptionBase() {
        return optionBase;
    }

    public List<String> getOptions() {
        if(options == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(options).toList();
    }

    public List<Component> getChildren() {
        if(subComponentId > -1) {
            return new ArrayList<>(children.values().stream().sorted(Comparator.comparingInt(Component::getSubComponentId)).toList());
        }
        return new ArrayList<>(children.values().stream().sorted(Comparator.comparingInt(Component::getComponentId)).toList());
    }

    public int getComponentId() {
        return componentId;
    }

    public int getSubComponentId() {
        return subComponentId;
    }

    public int getProperties() {
        return properties;
    }

    public int getFontId() {
        return fontId;
    }

    public int getColor() {
        return color;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getItemId() {
        return itemId;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public int getSpriteId() {
        return spriteId;
    }

    public Map<Integer, Object> getParams() {
        return params;
    }

    public int getParam(int id) {
        ParamType type = ConfigManager.getParamProvider().provide(id);
        if (type == null) {
            return -1;
        }
        Object value = params.get(id);
        if (value == null) {
            return type.getDefaultInt();
        }
        return (int) value;
    }

    public Component getSubComponent(int id) {
        return children.get(id);
    }

    @Override
    public int hashCode() {
        return root.getInterfaceId() << 16 | componentId;
    }
}
