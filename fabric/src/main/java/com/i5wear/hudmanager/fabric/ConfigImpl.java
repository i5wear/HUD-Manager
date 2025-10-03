package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.Config;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class ConfigImpl {

    public static Config get(ResourceLocation Name) { return Category.get(Name); }

    private static final Map<ResourceLocation, Config> Category = Map.ofEntries(
            Map.entry(VanillaHudElements.CROSSHAIR, Config.CROSSHAIR),
            Map.entry(VanillaHudElements.SPECTATOR_MENU, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.HOTBAR, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.ARMOR_BAR, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.HEALTH_BAR, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.FOOD_BAR, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.AIR_BAR, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.MOUNT_HEALTH, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.INFO_BAR, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.EXPERIENCE_LEVEL, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.HELD_ITEM_TOOLTIP, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.SPECTATOR_TOOLTIP, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.STATUS_EFFECTS, Config.STATUS_EFFECT),
            Map.entry(VanillaHudElements.BOSS_BAR, Config.BOSS_BAR),
            Map.entry(VanillaHudElements.SCOREBOARD, Config.SCOREBOARD_SIDEBAR),
            Map.entry(VanillaHudElements.OVERLAY_MESSAGE, Config.HOTBAR_GROUP),
            Map.entry(VanillaHudElements.TITLE_AND_SUBTITLE, Config.SCREEN_TITLE),
            Map.entry(VanillaHudElements.PLAYER_LIST, Config.PLAYER_LIST),
            Map.entry(VanillaHudElements.SUBTITLES, Config.CLOSED_CAPTION)
    );
}