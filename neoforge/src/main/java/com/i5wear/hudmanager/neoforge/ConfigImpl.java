package com.i5wear.hudmanager.neoforge;

import com.i5wear.hudmanager.Config;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.Map;

public class ConfigImpl {

    public static Config get(ResourceLocation Name) { return Category.get(Name); }

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
}