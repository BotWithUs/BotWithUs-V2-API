package net.botwithus.rs3.interfaces;

public enum ComponentType {
    LAYER(0),
    UNKNOWN(1), // Inventory or sprite grid, deprecated
    TABLE(2),
    BOX(3),
    TEXT(4),
    SPRITE(5),
    MODEL(6),
    MEDIA(7),
    TOOLTIP(8),
    DIVIDER(9),
    BUTTON(10),
    PANEL(11),
    CHECK(12), // As in checkmark
    INPUTS(13),
    SLIDER(14),
    GRID(15),
    LIST(16),
    COMBO(17),
    PAGED_LAYER(18),
    CAROUSEL(20),
    PAGED_CAROUSEL(21),
    RADIO_GROUP(22),
    GROUP_BOX(23),
    RADIAL_PROGRESS_OVERLAY(24),
    CRMVIEW(26),
    CUTSCENE(27), // No confirmation on the naming of this
    UNDEFINED(-1);

    private final int value;

    ComponentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ComponentType fromValue(int value) {
        for (ComponentType type : ComponentType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return UNDEFINED;
    }
}
