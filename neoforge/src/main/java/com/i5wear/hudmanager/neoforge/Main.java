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

    private static void modifyElement(RegisterGuiLayersEvent Registry) {
        for (Map.Entry<ResourceLocation, Config> Element : Category.entrySet()) {
            Registry.wrapLayer(
                    Element.getKey(), original -> (arg0, arg1) -> {
                        Config Value = Element.getValue();
                        if (Value.Show.get() && Value.Size.get() > 0) {
                            Config.CURRENT_SIZE = Value.Size.get();
                            arg0.pose().pushMatrix();
                            arg0.pose().scale(0.01f * Value.Size.get());
                            arg0.pose().translate(0.01f * Value.PosX.get() * arg0.guiWidth(), 0.01f * Value.PosY.get() * arg0.guiHeight());
                            original.render(arg0, arg1);
                            arg0.pose().popMatrix();
                            Config.CURRENT_SIZE = 100;
                        }
                    }
            );
        }
    }

    public Main(ModContainer Instance, IEventBus Event) {
        Instance.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        Instance.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        Event.addListener(Main::modifyElement);
    }
}