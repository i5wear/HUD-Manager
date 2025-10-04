package com.i5wear.hudmanager.neoforge;

import com.i5wear.hudmanager.Config;
import com.i5wear.hudmanager.Global;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.common.NeoForge;

import java.util.Map;

@Mod(value = Global.MOD_ID, dist = Dist.CLIENT)
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
            Map.entry(VanillaGuiLayers.OVERLAY_MESSAGE, Config.HOTBAR_GROUP),
            Map.entry(VanillaGuiLayers.TITLE, Config.SCREEN_TITLE),
            Map.entry(VanillaGuiLayers.TAB_LIST, Config.PLAYER_LIST),
            Map.entry(VanillaGuiLayers.SUBTITLE_OVERLAY, Config.CLOSED_CAPTION)
    );

    @SubscribeEvent private static void beforeElementRender(RenderGuiLayerEvent.Pre event) {
        Config Value = Category.get(event.getName());
        GuiGraphics arg0 = event.getGuiGraphics();
        if (Value == null) arg0.pose().pushMatrix();
        else if (Value.Size.get() == 0 || !Value.Show.get()) event.setCanceled(true);
        else {
            Global.CURRENT_SIZE = Value.Size.get();
            arg0.pose().pushMatrix();
            arg0.pose().scale(0.01f * Value.Size.get());
            arg0.pose().translate(0.01f * Value.PosX.get() * arg0.guiWidth(), 0.01f * Value.PosY.get() * arg0.guiHeight());
        }
    }

    @SubscribeEvent private static void afterElementRender(RenderGuiLayerEvent.Post event) {
        event.getGuiGraphics().pose().popMatrix();
        Global.CURRENT_SIZE = 100;
    }

    public Main(ModContainer instance) {
        instance.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        instance.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        NeoForge.EVENT_BUS.register(Main.class);
    }
}