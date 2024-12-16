package net.botwithus.rs3.cache.assets;

import net.botwithus.rs3.cache.Filesystem;
import net.botwithus.rs3.cache.assets.providers.*;
import net.botwithus.rs3.cache.sqlite.SqliteFilesystem;

import java.nio.file.Path;
import java.util.logging.Logger;

public final class ConfigManager {

    private static final Logger log = Logger.getLogger(ConfigManager.class.getName());

    private static Filesystem FS;

    private static ItemProvider itemProvider;

    private static ParamProvider paramProvider;

    private static VarBitProvider varBitProvider;

    private static NpcProvider npcProvider;

    private static InvProvider invProvider;

    private static LocProvider locProvider;

    private static ScriptProvider scriptProvider;

    private static VarProvider varProvider;

    private static MapProvider mapProvider;

    public static void initialize() {
        String cachePath = System.getProperty("cache_path");
        if (cachePath != null) {
            FS = new SqliteFilesystem(Path.of(cachePath));
            return;
        }
        String steamEnv = System.getenv("SteamEnv");
        if (steamEnv == null) {
            log.info("Steam was not found, using default directory.");
            FS = new SqliteFilesystem(Path.of("C:\\ProgramData\\Jagex\\RuneScape"));
        } else {
            String steamPath = System.getenv("SteamPath");
            log.info("Steam was found at: " + steamPath);
            System.out.println("Steam was found at: " + steamPath);
            FS = new SqliteFilesystem(Path.of(steamPath, "steamapps", "common", "RuneScape", "RuneScape"));
        }

        paramProvider = new ParamProvider(FS);
        itemProvider = new ItemProvider(FS, paramProvider);
        varBitProvider = new VarBitProvider(FS);
        npcProvider = new NpcProvider(FS);
        invProvider = new InvProvider(FS);
        locProvider = new LocProvider(FS);
        scriptProvider = new ScriptProvider(FS);
        varProvider = new VarProvider(FS);
        mapProvider = new MapProvider(FS);
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

    public static LocProvider getLocProvider() {
        return locProvider;
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
