package net.botwithus.ui;

import net.botwithus.ui.workspace.ExtInfo;
import net.botwithus.ui.workspace.Workspace;
import net.botwithus.ui.workspace.WorkspaceExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class WorkspaceManager implements Iterable<Workspace> {

    private static final Logger log = Logger.getLogger(WorkspaceManager.class.getName());

    private static final WorkspaceManager INSTANCE = new WorkspaceManager();

    private final List<Workspace> workspaces;

    private final AtomicBoolean isOpen;

    private Workspace current;


    public WorkspaceManager() {
        this.workspaces = new ArrayList<>();
        this.isOpen = new AtomicBoolean(false);
    }

    public Workspace newWorkspace() {
        Workspace workspace = new Workspace();
        ServiceLoader<WorkspaceExtension> load = ServiceLoader.load(WorkspaceExtension.class);
        for (ServiceLoader.Provider<WorkspaceExtension> provider : load.stream().toList()) {
            Class<? extends WorkspaceExtension> type = provider.type();
            if(!type.isAnnotationPresent(ExtInfo.class)) {
                continue;
            }
            workspace.getExtensions().add(provider.get());
        }
        this.workspaces.add(workspace);
        return workspace;
    }

    public void switchWorkspace(Workspace workspace) {
        this.current = workspace;
    }

    public boolean isOpen() {
        return isOpen.get();
    }

    public void setOpen(boolean isOpen) {
        this.isOpen.set(isOpen);
    }

    public Workspace getCurrent() {
        return current;
    }

    public static WorkspaceManager getManager() {
        return INSTANCE;
    }

    public static void save(Workspace workspace) {
        try {
            String home = System.getProperty("user.home");
            Path path = Path.of(home, ".botwithus", "workspaces");
            Files.createDirectories(path);

            workspace.save();

            Path file = path.resolve(workspace.getUuid() + ".properties");
            try {
                workspace.getProperties().store(Files.newOutputStream(file), null);
            } catch (IOException e) {
                log.log(Level.SEVERE, "Failed to save workspace: " + file, e);
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed to save workspaces.", e);
        }
    }

    public static void load() {
        String home = System.getProperty("user.home");
        Path path = Path.of(home, ".botwithus", "workspaces");
        try {
            Files.createDirectories(path);
            try(Stream<Path> files = Files.list(path)) {
                files.forEach(file -> {
                    Properties properties = new Properties();
                    try {
                        properties.load(Files.newInputStream(file));
                        Workspace workspace = INSTANCE.newWorkspace();
                        workspace.load(properties);
                    } catch (IOException e) {
                        log.log(Level.SEVERE, "Failed to load workspace: " + file, e);
                    }
                });
            }
            if(INSTANCE.workspaces.isEmpty()) {
                INSTANCE.current = INSTANCE.newWorkspace();
            } else {
                INSTANCE.current = INSTANCE.workspaces.getFirst();
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed to load workspaces.", e);
        }
    }

    public int size() {
        return this.workspaces.size();
    }

    @Override
    public Iterator<Workspace> iterator() {
        return this.workspaces.iterator();
    }
}
