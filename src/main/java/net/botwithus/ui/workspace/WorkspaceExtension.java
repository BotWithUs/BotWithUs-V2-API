package net.botwithus.ui.workspace;

import java.util.Properties;

public interface WorkspaceExtension {

    void drawExtension(Workspace workspace);

    void drawMenu(Workspace workspace);

    default void onLoad(Workspace workspace, Properties properties) {

    }

    default void onSave(Workspace workspace, Properties properties) {

    }
}
