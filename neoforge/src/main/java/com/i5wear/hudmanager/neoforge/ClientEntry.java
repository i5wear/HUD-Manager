package com.i5wear.hudmanager.neoforge;

import com.i5wear.hudmanager.Manager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.Map;
import java.util.stream.Stream;

@Mod(value = Manager.IDENTITY, dist = Dist.CLIENT)
public final class ClientEntry {

    private static void modifyElement(RegisterGuiLayersEvent event) {
        Stream.of(
            Map.entry(VanillaGuiLayers.CROSSHAIR, Manager.CROSSHAIR),
            Map.entry(VanillaGuiLayers.HOTBAR, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.PLAYER_HEALTH, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.ARMOR_LEVEL, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.FOOD_LEVEL, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.VEHICLE_HEALTH, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.AIR_LEVEL, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.CONTEXTUAL_INFO_BAR_BACKGROUND, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.EXPERIENCE_LEVEL, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.CONTEXTUAL_INFO_BAR, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.SELECTED_ITEM_NAME, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.SPECTATOR_TOOLTIP, Manager.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.EFFECTS, Manager.STATUS_EFFECT),
            Map.entry(VanillaGuiLayers.SCOREBOARD_SIDEBAR, Manager.SCOREBOARD),
            Map.entry(VanillaGuiLayers.OVERLAY_MESSAGE, Manager.ACTION_BAR),
            Map.entry(VanillaGuiLayers.TITLE, Manager.SCREEN_TITLE),
            Map.entry(VanillaGuiLayers.TAB_LIST, Manager.PLAYER_LIST)
        ).forEach(
            entry -> event.wrapLayer(
                entry.getKey(), original -> (graphics, tracker) -> {
                    if (entry.getValue().apply(graphics))
                        original.render(graphics, tracker);
                    Manager.reset(graphics);
                }
            )
        );
    }

    public ClientEntry(ModContainer loader) {
        loader.getEventBus().addListener(ClientEntry::modifyElement);
        loader.registerConfig(ModConfig.Type.CLIENT, Manager.CONFIG);
        loader.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}