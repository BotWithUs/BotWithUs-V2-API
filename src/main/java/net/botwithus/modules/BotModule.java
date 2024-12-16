package net.botwithus.modules;

import net.botwithus.ui.workspace.Workspace;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.logging.Logger;

public interface BotModule extends Runnable {

    default void onSave(Properties properties) {

    }

    default void onLoad(Properties properties) {

    }

    default void onDraw(Workspace workspace) {

    }

    boolean isVisible();

    void setVisible(boolean visible);

    void enable();

    void disable();

    Logger log = Logger.getLogger(BotModule.class.getName());

    static void save(BotModule module) {
        try {
            BotModuleInfo info = module.getClass().getAnnotation(BotModuleInfo.class);
            if(info == null) {
                return;
            }
            String home = System.getProperty("user.home");
            Path path = Paths.get(home, ".botwithus", "modules");
            Files.createDirectories(path);
            Path file = path.resolve(info.name() + ".properties");
            Properties properties = new Properties();
            module.onSave(properties);
            properties.store(Files.newOutputStream(file), "Bot Module: " + info.name());
        } catch (Exception e) {
            log.throwing(BotModule.class.getName(), "save", e);
        }
    }

    static void load() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, ".botwithus", "modules");
        try {
            Files.createDirectories(path);
        } catch (Exception e) {
            log.throwing(BotModule.class.getName(), "load", e);
            return;
        }
        try(var stream = Files.list(path)) {
            stream.forEach(file -> {
                Properties properties = new Properties();
                try {
                    properties.load(Files.newInputStream(file));
                } catch (Exception e) {
                    log.throwing(BotModule.class.getName(), "load", e);
                    return;
                }
                String name = file.getFileName().toString();
                name = name.substring(0, name.length() - ".properties".length());
                for (BotModule module : ServiceLoader.load(BotModule.class)) {
                    BotModuleInfo info = module.getClass().getAnnotation(BotModuleInfo.class);
                    if(info == null) {
                        continue;
                    }
                    if(info.name().equals(name)) {
                        module.onLoad(properties);
                        break;
                    }
                }
            });
        } catch (Exception e) {
            log.throwing(BotModule.class.getName(), "load", e);
        }
    }
}
