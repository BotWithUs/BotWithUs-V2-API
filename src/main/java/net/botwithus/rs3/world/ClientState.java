package net.botwithus.rs3.world;

public enum ClientState {

    LOGIN(10),
    LOBBY(20),
    GAME(30);

    private final int id;

    ClientState(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private static final ClientState[] values = values();

    public static ClientState fromId(int id) {
        for (ClientState state : values) {
            if (state.getId() == id) {
                return state;
            }
        }
        return null;
    }

}
