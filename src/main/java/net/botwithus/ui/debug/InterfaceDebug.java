package net.botwithus.ui.debug;

import net.botwithus.imgui.ImFlags;
import net.botwithus.imgui.ImGui;
import net.botwithus.rs3.cache.assets.ConfigManager;
import net.botwithus.rs3.cache.assets.params.ParamDefinition;
import net.botwithus.rs3.interfaces.Component;
import net.botwithus.rs3.interfaces.ComponentType;
import net.botwithus.rs3.interfaces.Interface;
import net.botwithus.rs3.interfaces.InterfaceManager;
import net.botwithus.ui.workspace.ExtInfo;
import net.botwithus.ui.workspace.Workspace;
import net.botwithus.ui.workspace.WorkspaceExtension;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

@ExtInfo(name = "Interfaces")
public final class InterfaceDebug implements WorkspaceExtension {

    private static final Logger log = Logger.getLogger(InterfaceDebug.class.getName());

    private boolean isVisible = false;

    private Component selected;

    private String text = "";
    private String option = "";

    private int itemId;

    private boolean isOpen;

    private Predicate<Component> filter = _ -> true;

    @Override
    public void drawExtension(Workspace workspace) {

        if (isVisible) {
            if (ImGui.begin("Interface Debug", 0)) {
                text = ImGui.inputTextWithHint("Text", "Component Text", text, 0);
                option = ImGui.inputTextWithHint("Option", "Component Option", option, 0);
                itemId = ImGui.inputInt("Item ID", itemId, 1, 1, 0);
                isOpen = ImGui.checkbox("Open", isOpen);
                if(ImGui.button("Apply Filter", 0, 0)) {
                    filter = _ -> false;
                    if(!text.isEmpty()) {
                        log.info("Filtering by text: " + text);
                        filter = filter.or((component) -> component.getText().toLowerCase().contains(text.toLowerCase()));
                    }
                    if(!option.isEmpty()) {
                        log.info("Filtering by option: " + option);
                        filter = filter.or((component) -> component.getOptions().stream().anyMatch(s -> s.toLowerCase().contains(option.toLowerCase())));
                    }
                    if(itemId > 0) {
                        log.info("Filtering by item id: " + itemId);
                        filter = filter.or((component) -> component.getItemId() == itemId);
                    }
                }
                ImGui.sameLine(0, 5);
                if(ImGui.button("Clear Filter", 0, 0)) {
                    filter = _ -> true;
                }

                ImGui.separator();
                ImGui.columns(2, "interface_debug", true);
                drawInterfaces();
                ImGui.nextColumn();
                drawSelectedComponent();
                ImGui.columns(0, "interface_debug", false);
            }
            ImGui.end();
        }

    }

    private void drawSelectedComponent() {
        if (selected == null) {
            ImGui.text("No component selected");
        } else {
            if (ImGui.beginTable("##selected_comp_table", 2, ImFlags.ImGuiTable_Nice_Borders, 0, 0, 0)) {
                ImGui.tableSetupColumn("Field", 0, 0, 0);
                ImGui.tableSetupColumn("Value", 0, 0, 0);
                ImGui.tableHeadersRow();

                try {
                    interfaceRow("Interface ID", selected.getRoot().getInterfaceId());
                    interfaceRow("Component ID", selected.getComponentId());
                    interfaceRow("Sub Component ID", selected.getSubComponentId());
                    interfaceRow("Type", selected.getType().name());
                    if (selected.getOptionBase() != null && !selected.getOptionBase().isEmpty()) {
                        interfaceRow("Option Base", selected.getOptionBase());
                    }

                    if (selected.getOptions() != null && !selected.getOptions().isEmpty()) {
                        int index = 0;
                        for (String option : selected.getOptions()) {
                            interfaceRow("Option " + index, option);
                            index++;
                        }
                    }

                    if (selected.getType() == ComponentType.SPRITE) {
                        interfaceRow("Sprite ID", selected.getSpriteId());
                        interfaceRow("Item ID", selected.getItemId());
                        interfaceRow("Item Amount", selected.getItemAmount());
                    }
                    if(selected.getText() != null && !selected.getText().isEmpty()) {
                        interfaceRow("Text", selected.getText());
                        interfaceRow("Font ID", selected.getFontId());
                        interfaceRow("Text Color", selected.getColor());
                        interfaceRow("Alpha", selected.getAlpha());
                    }
                } catch (Exception e) {
                    log.log(java.util.logging.Level.SEVERE, "Error drawing component", e);
                }

                ImGui.endTable();
            }
            ImGui.separatorText("Component Params");
            if (selected.getParams().isEmpty()) {
                ImGui.text("No params");
            } else if (ImGui.beginTable("##selected_comp_params_table", 2, ImFlags.ImGuiTable_Nice_Borders, 0, 0, 0)) {
                ImGui.tableSetupColumn("Type", 0, 0, 0);
                ImGui.tableSetupColumn("Value", 0, 0, 0);
                ImGui.tableHeadersRow();
                for (int param : selected.getParams().keySet()) {
                    ParamDefinition type = ConfigManager.getParamProvider().provide(param);
                    if (type == null) {
                        continue;
                    }
                    interfaceRow(type.getVarType().name() + " (" + param + ")", selected.getParams().get(param));
                }
                ImGui.endTable();
            }
        }
    }

    private void interfaceRow(String label, Object value) {
        ImGui.tableNextRow(0, 0);
        ImGui.tableNextColumn();
        ImGui.text(label);
        ImGui.tableNextColumn();
        ImGui.text(value == null ? "null" : value.toString());
    }

    private void drawComponent(Component component) {
        int flags = 0;
        if (component.getChildren().isEmpty()) {
            flags |= ImFlags.ImGuiTreeNodeFlags_Leaf;
        }
        String label = component.getType().name() + " " + component.getComponentId();
        if (component.getSubComponentId() > -1) {
            label += " - " + component.getSubComponentId();
        }
        if(!component.getParams().isEmpty()) {
            label += " *";
        }
        boolean isOpen = ImGui.treeNode(label, flags);
        if (ImGui.isItemClicked(0)) {
            selected = component;
        }

        if (isOpen) {
            if (component.getType() == ComponentType.LAYER) {
                for (Component child : component.getChildren().stream().filter(filter).toList()) {
                    drawComponent(child);
                }
            }
            ImGui.treePop();
        }
    }

    private void drawInterfaces() {
        if (ImGui.beginListBox("##interface_list", 200, -1)) {
            for (Interface inter : InterfaceManager.getInterfaces()) {
                if(isOpen && !inter.isOpen()) {
                    continue;
                }
                List<Component> comps = inter.stream().filter(filter).toList();
                if(comps.isEmpty()) {
                    continue;
                }
                if (ImGui.treeNode("Interface " + inter.getInterfaceId(), 0)) {
                    for (Component component : comps) {
                        drawComponent(component);
                    }
                    ImGui.treePop();
                }
            }
            ImGui.endListBox();
        }
    }

    @Override
    public void drawMenu(Workspace workspace) {
        isVisible = ImGui.menuItem("Interface Debug", null, isVisible, true);
    }
}
