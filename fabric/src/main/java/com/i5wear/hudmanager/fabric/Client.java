package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.HudOptions;
import com.i5wear.hudmanager.HudManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class Client implements ClientModInitializer {

    private static final Map<ResourceLocation, HudManager> CATEGORY = Map.ofEntries(
        Map.entry(VanillaHudElements.CROSSHAIR, HudOptions.INSTANCE.Crosshair),
        Map.entry(VanillaHudElements.SPECTATOR_MENU, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.HOTBAR, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.ARMOR_BAR, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.HEALTH_BAR, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.FOOD_BAR, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.AIR_BAR, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.MOUNT_HEALTH, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.INFO_BAR, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.EXPERIENCE_LEVEL, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.HELD_ITEM_TOOLTIP, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.SPECTATOR_TOOLTIP, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.STATUS_EFFECTS, HudOptions.INSTANCE.StatusEffect),
        Map.entry(VanillaHudElements.SCOREBOARD, HudOptions.INSTANCE.Scoreboard),
        Map.entry(VanillaHudElements.OVERLAY_MESSAGE, HudOptions.INSTANCE.ActionBar),
        Map.entry(VanillaHudElements.TITLE_AND_SUBTITLE, HudOptions.INSTANCE.ScreenTitle),
        Map.entry(VanillaHudElements.PLAYER_LIST, HudOptions.INSTANCE.PlayerList)
    );

    private static void modifyElement() {
        CATEGORY.forEach(
            (key, value) -> HudElementRegistry.replaceElement(
                key, original -> (graphics, tracker) -> {
                    if (value.apply(graphics))
                        original.render(graphics, tracker);
                    if (key == VanillaHudElements.HOTBAR) // Patch #13
                        graphics.pose().popMatrix();
                    else HudManager.reset(graphics);
                }
            )
        );
    }

    @Override public void onInitializeClient() {
        HudOptions.CONFIG = FabricLoader.getInstance().getConfigDir().resolve("hudmanager.json").toFile();
        HudOptions.load(); modifyElement();
    }
}