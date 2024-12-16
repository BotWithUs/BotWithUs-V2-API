package net.botwithus.rs3.minimenu;

public enum Action {
    WALK(23),
    PLAYER1(44),
    PLAYER2(45),
    PLAYER3(46),
    PLAYER4(47),
    PLAYER5(48),
    PLAYER6(49),
    PLAYER7(50),
    PLAYER8(51),
    NPC1(9),
    NPC2(10),
    NPC3(11),
    NPC4(12),
    NPC5(13),
    NPC6(1003),
    COMPONENT_ITEM(58),
    OBJECT1(3),
    OBJECT2(4),
    OBJECT3(5),
    OBJECT4(6),
    OBJECT5(1001),
    OBJECT6(1002),
    COMPONENT(57),
    GROUND_ITEM1(18),
    GROUND_ITEM2(19),
    GROUND_ITEM3(20),
    GROUND_ITEM4(21),
    GROUND_ITEM5(22),
    GROUND_ITEM6(1004),
    DIALOGUE(30),
    SELECTABLE_COMPONENT(25),
    SELECT_COMPONENT_ITEM(58),
    SELECT_GROUND_ITEM(17),
    SELECT_NPC(8),
    SELECT_OBJECT(2),
    SELECT_PLAYER(15),
    SELECT_TILE(59);

    private final int id;

    Action(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private static final Action[] values = values();

    public static Action byId(int id) {
        for (Action action : values) {
            if (action.id == id) {
                return action;
            }
        }
        return null;
    }

    public static boolean isSceneObjectAction(Action action) {
        return action == OBJECT1 || action == OBJECT2 || action == OBJECT3 || action == OBJECT4 || action == OBJECT5 || action == OBJECT6;
    }

    public static boolean isPlayerAction(Action action) {
        return action == PLAYER1 || action == PLAYER2 || action == PLAYER3 || action == PLAYER4 || action == PLAYER5 || action == PLAYER6 || action == PLAYER7 || action == PLAYER8;
    }

    public static boolean isNpcAction(Action action) {
        return action == NPC1 || action == NPC2 || action == NPC3 || action == NPC4 || action == NPC5 || action == NPC6;
    }

    public static boolean isGroundItemAction(Action action) {
        return action == GROUND_ITEM1 || action == GROUND_ITEM2 || action == GROUND_ITEM3 || action == GROUND_ITEM4 || action == GROUND_ITEM5 || action == GROUND_ITEM6;
    }

    public static boolean isComponentAction(Action action) {
        return action == COMPONENT || action == COMPONENT_ITEM;
    }

    public static boolean isSelectableAction(Action action) {
        return action == SELECTABLE_COMPONENT || action == SELECT_COMPONENT_ITEM || action == SELECT_GROUND_ITEM || action == SELECT_NPC || action == SELECT_OBJECT || action == SELECT_PLAYER || action == SELECT_TILE;
    }

    public static boolean isDialogueAction(Action action) {
        return action == DIALOGUE;
    }

    public static boolean isWalkAction(Action action) {
        return action == WALK;
    }
}