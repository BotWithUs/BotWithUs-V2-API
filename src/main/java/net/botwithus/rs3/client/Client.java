package net.botwithus.rs3.client;

import net.botwithus.rs3.client.internal.MutableClient;
import net.botwithus.rs3.world.ClientState;

public sealed abstract class Client permits MutableClient {

    public static int getClientCycle() {
        return MutableClient.CLIENT_CYCLE;
    }

    public static int getServerTick() {
        return MutableClient.SERVER_TICK;
    }

    public static ClientState getClientState() {
        return ClientState.fromId(MutableClient.CLIENT_STATE);
    }

}
