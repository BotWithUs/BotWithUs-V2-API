package net.botwithus.rs3.entities;

import net.botwithus.rs3.cache.assets.ConfigManager;
import net.botwithus.rs3.cache.assets.locs.LocType;
import net.botwithus.rs3.entities.internal.MutableSceneObject;
import net.botwithus.rs3.minimenu.Action;
import net.botwithus.rs3.minimenu.Interactive;
import net.botwithus.rs3.minimenu.MiniMenu;
import net.botwithus.rs3.vars.VarDomain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public sealed abstract class SceneObject extends Entity implements Interactive permits MutableSceneObject {
    private static final Logger log = Logger.getLogger(SceneObject.class.getName());

    protected int typeId;
    protected int animationId;

    protected boolean hidden;

    public SceneObject(EntityType type) {
        super(type);
    }

    public int getTypeId() {
        return typeId;
    }

    public int getAnimationId() {
        return animationId;
    }

    public boolean isHidden() {
        return hidden;
    }

    public LocType getMultiType() {
        LocType type = ConfigManager.getLocProvider().provide(this.typeId);
        if (type == null) {
            return null;
        }
        int[] immitations = type.getImmitations();
        if(immitations == null) {
            return type;
        }

        int value = -1;
        if(type.getVarp() != -1) {
            value = VarDomain.getVarValue(type.getVarp());
        } else if(type.getVarbit() != -1) {
            value = VarDomain.getVarBitValue(type.getVarbit());
        }
        if(value < 0 || value >= immitations.length - 1) {
            value = immitations.length - 1;
            if(value != -1) {
                return ConfigManager.getLocProvider().provide(immitations[value]);
            }
            return type;
        }
        if(immitations[value] == -1) {
            return type;
        }
        return ConfigManager.getLocProvider().provide(immitations[value]);
    }

    public String getName() {
        LocType type = getMultiType();
        if (type == null) {
            return "";
        }
        if (type.getName() == null) {
            return "";
        }
        return type.getName();
    }

    public List<String> getOptions() {
        LocType type = getMultiType();
        if (type == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(type.getOptions()).toList();
    }

    @Override
    public boolean interact() {
        return interact(0);
    }

    @Override
    public boolean interact(int option) {
        Action action = switch (option) {
            case 1 -> Action.OBJECT1;
            case 2 -> Action.OBJECT2;
            case 3 -> Action.OBJECT3;
            case 4 -> Action.OBJECT4;
            case 5 -> Action.OBJECT5;
            case 6 -> Action.OBJECT6;
            default -> Action.OBJECT1;
        };
        return MiniMenu.doAction(action, typeId, coordinate.x(), coordinate.y());
    }

    @Override
    public boolean interact(String option) {
        List<String> options = getOptions();
        for (int i = 0; i < options.size(); i++) {
            String opt = options.get(i);
            if (opt == null) {
                continue;
            }
            if (opt.equals(option)) {
                return interact(i + 1);
            }
        }
        return false;
    }

    @Override
    public boolean interact(Pattern pattern) {
        List<String> options = getOptions();
        for (int i = 0; i < options.size(); i++) {
            String option = options.get(i);
            if (option == null) {
                continue;
            }
            if (pattern.matcher(option).matches()) {
                return interact(i + 1);
            }
        }
        return false;
    }
}
