package net.botwithus.rs3.interfaces.internal;

import net.botwithus.rs3.interfaces.Component;
import net.botwithus.rs3.interfaces.ComponentType;

import java.util.Map;

public final class MutableComponent extends Component {

    public MutableComponent(ComponentType type) {
        super(type);
    }

    public void setRoot(MutableInterface root) {
        this.root = root;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptionBase(String optionBase) {
        this.optionBase = optionBase;
    }

    public void addChild(MutableComponent child) {
        if(type != ComponentType.LAYER) {
            throw new IllegalStateException("Cannot add a child to a non-layer component");
        }
        children.put(child.getSubComponentId(), child);
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public void setSubComponentId(int subComponentId) {
        this.subComponentId = subComponentId;
    }

    public void setProperties(int properties) {
        this.properties = properties;
    }

    public void setFontId(int fontId) {
        this.fontId = fontId;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public void setSpriteId(int spriteId) {
        this.spriteId = spriteId;
    }

    public Map<Integer, Object> getParams() {
        return params;
    }

    public MutableComponent getSubComponent(int id) {
        return (MutableComponent) children.get(id);
    }

    public void setOption(int index, String option) {
        this.options[index] = option;
    }
}
