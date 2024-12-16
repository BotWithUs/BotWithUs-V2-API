module BotWithUs.api {
    uses net.botwithus.scripts.Script;
    uses net.botwithus.scripts.ScriptRepository;
    uses net.botwithus.rs3.entities.LocalPlayer;
    uses net.botwithus.rs3.minimenu.MiniMenu;
    uses net.botwithus.rs3.vars.VarDomain;
    uses net.botwithus.ui.workspace.WorkspaceExtension;
    uses net.botwithus.modules.BotModule;

    exports net.botwithus.events;
    exports net.botwithus.scripts;
    exports net.botwithus.scripts.repositories;
    exports net.botwithus.rs3.world;
    exports net.botwithus.rs3.entities;
    exports net.botwithus.rs3.minimenu;
    exports net.botwithus.rs3.vars;
    exports net.botwithus.rs3.cache;
    exports net.botwithus.rs3.cache.assets;
    exports net.botwithus.rs3.cache.assets.cs2;
    exports net.botwithus.rs3.cache.assets.items;
    exports net.botwithus.rs3.cache.assets.npcs;
    exports net.botwithus.rs3.cache.assets.params;
    exports net.botwithus.rs3.cache.assets.vars;
    exports net.botwithus.rs3.cache.assets.locs;
    exports net.botwithus.rs3.cache.assets.inventories;
    exports net.botwithus.rs3.cache.assets.providers;
    exports net.botwithus.rs3.inventories;
    exports net.botwithus.rs3.item;
    exports net.botwithus.rs3.inventories.events;
    exports net.botwithus.rs3.interfaces;
    exports net.botwithus.rs3.stats;
    exports net.botwithus.util;
    exports net.botwithus.rs3.cs2;
    exports net.botwithus.ui;
    exports net.botwithus.ui.workspace;
    exports net.botwithus.modules;
    exports net.botwithus.modules.login;
    exports net.botwithus.modules.vars;
    exports net.botwithus.ui.debug;
    exports net.botwithus.logging;
    exports net.botwithus.rs3.login;
    exports net.botwithus.rs3.client;
    exports net.botwithus.rs3.cache.assets.maps;

    exports net.botwithus.rs3.inventories.internal to BotWithUs.internal;
    exports net.botwithus.rs3.item.internal to BotWithUs.internal;
    exports net.botwithus.rs3.world.internal to BotWithUs.internal;
    exports net.botwithus.rs3.entities.internal to BotWithUs.internal;
    exports net.botwithus.rs3.interfaces.internal to BotWithUs.internal;
    exports net.botwithus.rs3.stats.internal to BotWithUs.internal;
    exports net.botwithus.rs3.cs2.internal to BotWithUs.internal;
    exports net.botwithus.rs3.login.internal to BotWithUs.internal;
    exports net.botwithus.rs3.client.internal to BotWithUs.internal;

    requires java.logging;
    requires BotWithUs.imgui;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.apache.commons.compress;

    provides net.botwithus.scripts.ScriptRepository with net.botwithus.scripts.repositories.LocalRepository;
    provides net.botwithus.ui.workspace.WorkspaceExtension with net.botwithus.ui.debug.DebugWindow, net.botwithus.ui.debug.InterfaceDebug, net.botwithus.ui.debug.GameWorldDebug, net.botwithus.ui.debug.InventoryDebug;
    provides net.botwithus.modules.BotModule with net.botwithus.modules.login.AutoLogin, net.botwithus.modules.vars.VarMonitorModule;
}