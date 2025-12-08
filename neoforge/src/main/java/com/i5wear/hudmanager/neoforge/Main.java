package com.i5wear.hudmanager.neoforge;

import com.i5wear.hudmanager.Manager;
import com.i5wear.hudmanager.config.ConfigScreen;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.Map;

@Mod(value = Manager.IDENTITY, dist = Dist.CLIENT)
public class Main {

    private static final Map<ResourceLocation, Manager> CATEGORY = Map.ofEntries(
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
        Map.entry(VanillaGuiLayers.SCOREBOARD_SIDEBAR, Manager.SCOREBOARD_SIDEBAR),
        Map.entry(VanillaGuiLayers.OVERLAY_MESSAGE, Manager.ACTION_BAR),
        Map.entry(VanillaGuiLayers.TITLE, Manager.SCREEN_TITLE),
        Map.entry(VanillaGuiLayers.TAB_LIST, Manager.PLAYER_LIST)
    );

    private static void modifyElement(RegisterGuiLayersEvent event) {
        CATEGORY.forEach(
            (key, value) -> event.wrapLayer(
                key, original -> (graphics, tracker) -> {
                    if (value.apply(graphics))
                        original.render(graphics, tracker);
                    Manager.reset(graphics);
                }
            )
        );
    }

    public Main(ModContainer container, IEventBus event) {
        container.registerConfig(ModConfig.Type.CLIENT, Manager.CONFIG);
        container.registerExtensionPoint(IConfigScreenFactory.class, (ignored, parent) -> new ConfigScreen(parent));
        event.addListener(Main::modifyElement);
    }
}