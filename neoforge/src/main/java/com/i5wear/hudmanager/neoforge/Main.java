package com.i5wear.hudmanager.neoforge;

import com.i5wear.hudmanager.Config;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.Map;

@Mod(value = Config.MOD_ID, dist = Dist.CLIENT)
public class Main {

    private static final Map<ResourceLocation, Config> Category = Map.ofEntries(
            Map.entry(VanillaGuiLayers.CROSSHAIR, Config.CROSSHAIR),
            Map.entry(VanillaGuiLayers.HOTBAR, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.PLAYER_HEALTH, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.ARMOR_LEVEL, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.FOOD_LEVEL, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.VEHICLE_HEALTH, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.AIR_LEVEL, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.CONTEXTUAL_INFO_BAR_BACKGROUND, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.EXPERIENCE_LEVEL, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.CONTEXTUAL_INFO_BAR, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.SELECTED_ITEM_NAME, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.SPECTATOR_TOOLTIP, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.EFFECTS, Config.STATUS_EFFECT),
            Map.entry(VanillaGuiLayers.BOSS_OVERLAY, Config.BOSS_BAR),
            Map.entry(VanillaGuiLayers.SCOREBOARD_SIDEBAR, Config.SCOREBOARD_SIDEBAR),
            Map.entry(VanillaGuiLayers.OVERLAY_MESSAGE, Config.ACTION_BAR),
            Map.entry(VanillaGuiLayers.TITLE, Config.SCREEN_TITLE),
            Map.entry(VanillaGuiLayers.TAB_LIST, Config.PLAYER_LIST),
            Map.entry(VanillaGuiLayers.SUBTITLE_OVERLAY, Config.CLOSED_CAPTION)
    );

    private static void modifyElement(RegisterGuiLayersEvent event) {
        for (Map.Entry<ResourceLocation, Config> Element : Category.entrySet()) {
            event.wrapLayer(
                Element.getKey(), original -> (instance, delta) -> {
                    Config Value = Element.getValue();
                    if (Value.Show.get() && Value.Size.get() > 0) {
                        Config.CURRENT_SIZE = Value.Size.get();
                        instance.pose().pushMatrix();
                        instance.pose().scale(0.01f * Value.Size.get());
                        instance.pose().translate(0.01f * Value.PosX.get() * instance.guiWidth(), 0.01f * Value.PosY.get() * instance.guiHeight());
                        original.render(instance, delta);
                        instance.pose().popMatrix();
                        Config.CURRENT_SIZE = 100;
                    }
                }
            );
        }
    }

    public Main(ModContainer instance, IEventBus event) {
        instance.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        instance.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        event.addListener(Main::modifyElement);
    }
}