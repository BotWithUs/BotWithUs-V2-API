package net.botwithus.ui.debug;

import net.botwithus.imgui.ImFlags;
import net.botwithus.imgui.ImGui;
import net.botwithus.rs3.cache.assets.ConfigManager;
import net.botwithus.rs3.cache.assets.vars.VarBitType;
import net.botwithus.rs3.cache.assets.vars.VarDomainType;
import net.botwithus.rs3.cache.assets.vars.VarType;
import net.botwithus.rs3.inventories.Inventory;
import net.botwithus.rs3.inventories.InventoryManager;
import net.botwithus.rs3.item.InvItem;
import net.botwithus.ui.workspace.ExtInfo;
import net.botwithus.ui.workspace.Workspace;
import net.botwithus.ui.workspace.WorkspaceExtension;

import java.util.Map;
import java.util.Properties;

@ExtInfo(name = "Inventory Debug")
public class InventoryDebug implements WorkspaceExtension {

    private boolean isInventoryDebugVisible = false;

    private InvItem selectedItem;

    @Override
    public void drawExtension(Workspace workspace) {
        if (isInventoryDebugVisible) {
            drawInventoryDebug(workspace);
        }
    }

    private void drawInventoryDebug(Workspace workspace) {
        if (ImGui.begin("Inventories", 0)) {
            ImGui.columns(2, "inv_cols", true);
            if (ImGui.beginListBox("", 400, -1)) {
                for (Inventory inventory : InventoryManager.getInventories()) {
                    if (ImGui.treeNode("Inventory " + inventory.getId() + (inventory.hasDomains() ? " (*)" : ""))) {
                        for (InvItem item : inventory.getItems()) {
                            if (item.getId() == -1) {
                                continue;
                            }
                            Map<Integer, Integer> domain = inventory.getDomain(item.getSlot());
                            String msg = item.getName() + " (" + item.getSlot() + ", " + item.getId() + ", " + item.getQuantity() + ")";
                            if(domain != null && !domain.isEmpty()) {
                                msg = "(*) " + msg;
                            }
                            boolean isOpen = ImGui.treeNode(msg, ImFlags.ImGuiTreeNodeFlags_Leaf);
                            if (ImGui.isItemClicked(0)) {
                                selectedItem = item;
                            }
                            if (isOpen) {
                                ImGui.treePop();
                            }
                        }
                        ImGui.treePop();
                    }
                }
                ImGui.endListBox();
            }
            ImGui.nextColumn();
            if (selectedItem != null) {
                ImGui.separatorText("Slot " + selectedItem.getSlot() + " Information");
                try {
                    ImGui.text("Slot: " + selectedItem.getSlot());
                    ImGui.text("ID: " + selectedItem.getId());
                    ImGui.text("Name: " + selectedItem.getName());
                    ImGui.text("Quantity: " + selectedItem.getQuantity());
                    ImGui.separatorText("Vars");

                    if(ImGui.beginTabBar("vars_tabs", 0)) {
                        if(ImGui.beginTabItem("Vars", 0)) {
                            drawVarValues();
                            ImGui.endTabItem();
                        }
                        if(ImGui.beginTabItem("Varbits", 0)) {
                            drawVarbitValues();
                            ImGui.endTabItem();
                        }
                        ImGui.endTabBar();
                    }



                } catch (Exception e) {
                    ImGui.text("Error: " + e.getMessage());
                }
            } else {
                ImGui.text("Select an item to view its details.");
            }
            ImGui.columns(0, "", false);
        }
        ImGui.end();
    }

    private void drawVarValues() {
        if (ImGui.beginTable("##var_domain" + selectedItem.getSlot(), 3, ImFlags.ImGuiTable_Nice_Borders, 0, 0, 0)) {
            ImGui.tableSetupColumn("ID", 0, 0, 0);
            ImGui.tableSetupColumn("Value", 0, 0, 0);

            ImGui.tableHeadersRow();
            Map<Integer, Integer> domain = selectedItem.getInventory().getDomain(selectedItem.getSlot());
            if (domain != null) {
                for (Map.Entry<Integer, Integer> entry : domain.entrySet()) {
                    int value = entry.getValue();
                    if (value > 0) {
                        ImGui.tableNextRow(0, 0);
                        ImGui.tableNextColumn();
                        ImGui.text(String.valueOf(entry.getKey()));
                        ImGui.tableNextColumn();
                        ImGui.text(String.valueOf(value));
                    }
                }
            }
            ImGui.endTable();
        }
    }

    private void drawVarbitValues() {
        if (ImGui.beginTable("##varbit_domain" + selectedItem.getSlot(), 2, ImFlags.ImGuiTable_Nice_Borders, 0, 0, 0)) {
            ImGui.tableSetupColumn("ID", 0, 0, 0);
            ImGui.tableSetupColumn("Value", 0, 0, 0);

            ImGui.tableHeadersRow();
            for (int i = 0; i < ConfigManager.getVarBitProvider().capacity(); i++) {
                Map<Integer, Integer> domain = selectedItem.getInventory().getDomain(selectedItem.getSlot());
                if (domain == null) {
                    continue;
                }
                VarBitType type = ConfigManager.getVarBitProvider().provide(i);
                if (type == null) {
                    continue;
                }
                if (type.getDomainType() != VarDomainType.OBJECT) {
                    continue;
                }
                for (Map.Entry<Integer, Integer> entry : domain.entrySet()) {
                    if (entry.getKey() == type.getVarId()) {
                        int value = entry.getValue();
                        int mask = (1 << ((type.getMsb() - type.getLsb()) + 1)) - 1;
                        value = (value >> type.getLsb()) & mask;
                        if (value > 0) {
                            ImGui.tableNextRow(0, 0);
                            ImGui.tableNextColumn();
                            ImGui.text(String.valueOf(i));
                            ImGui.tableNextColumn();
                            ImGui.text(String.valueOf(value));
                        }
                    }
                }
            }
            ImGui.endTable();
        }
    }

    @Override
    public void drawMenu(Workspace workspace) {
        isInventoryDebugVisible = ImGui.menuItem("Inventory Debug", null, isInventoryDebugVisible, true);
    }

    @Override
    public void onLoad(Workspace workspace, Properties properties) {
        WorkspaceExtension.super.onLoad(workspace, properties);
        isInventoryDebugVisible = Boolean.parseBoolean(properties.getProperty("isInventoryDebugVisible", "false"));
    }

    @Override
    public void onSave(Workspace workspace, Properties properties) {
        WorkspaceExtension.super.onSave(workspace, properties);
        properties.setProperty("isInventoryDebugVisible", String.valueOf(isInventoryDebugVisible));
    }
}
