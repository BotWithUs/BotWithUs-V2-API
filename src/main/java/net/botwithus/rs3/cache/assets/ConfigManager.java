package net.botwithus.rs3.cache.assets;

import net.botwithus.rs3.cache.CacheLibrary;
import net.botwithus.rs3.cache.assets.providers.*;

import java.util.logging.Logger;

public final class ConfigManager {

    private static final Logger log = Logger.getLogger(ConfigManager.class.getName());

    private static CacheLibrary cacheLibrary;

    private static ItemProvider itemProvider;

    private static ParamProvider paramProvider;

    private static VarBitProvider varBitProvider;

    private static NpcProvider npcProvider;

    private static InvProvider invProvider;

    private static SceneObjectProvider sceneObjectProvider;

    private static ScriptProvider scriptProvider;

    private static VarProvider varProvider;

    private static MapProvider mapProvider;

    public static void initialize(CacheLibrary library) {
        cacheLibrary = library;
        paramProvider = new ParamProvider(cacheLibrary);
        itemProvider = new ItemProvider(cacheLibrary, paramProvider);
        varBitProvider = new VarBitProvider(cacheLibrary);
        npcProvider = new NpcProvider(cacheLibrary);
        invProvider = new InvProvider(cacheLibrary);
        sceneObjectProvider = new SceneObjectProvider(cacheLibrary);
        scriptProvider = new ScriptProvider(cacheLibrary);
        varProvider = new VarProvider(cacheLibrary);
        mapProvider = new MapProvider(cacheLibrary);
    }

    public static ItemProvider getItemProvider() {
        return itemProvider;
    }

    public static ParamProvider getParamProvider() {
        return paramProvider;
    }

    public static VarBitProvider getVarBitProvider() {
        return varBitProvider;
    }


    public static NpcProvider getNpcProvider() {
        return npcProvider;
    }

    public static InvProvider getInvProvider() {
        return invProvider;
    }

    public static SceneObjectProvider getSceneObjectLoader() {
        return sceneObjectProvider;
    }

    public static ScriptProvider getScriptProvider() {
        return scriptProvider;
    }

    public static VarProvider getVarProvider() {
        return varProvider;
    }

    public static MapProvider getMapProvider() {
        return mapProvider;
    }
}
