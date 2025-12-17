package com.github.i5wear.hudmanager.fabric;

import com.github.i5wear.hudmanager.ModOptions;
import com.github.i5wear.hudmanager.HudManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.loader.api.FabricLoader;

import java.util.Map;

public class Client implements ClientModInitializer {

    private static void modifyElement() {
        Map.ofEntries(
            Map.entry(VanillaHudElements.CROSSHAIR, ModOptions.INSTANCE.Crosshair),
            Map.entry(VanillaHudElements.SPECTATOR_MENU, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.HOTBAR, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.ARMOR_BAR, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.HEALTH_BAR, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.FOOD_BAR, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.AIR_BAR, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.MOUNT_HEALTH, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.INFO_BAR, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.EXPERIENCE_LEVEL, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.HELD_ITEM_TOOLTIP, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.SPECTATOR_TOOLTIP, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaHudElements.STATUS_EFFECTS, ModOptions.INSTANCE.StatusEffect),
            Map.entry(VanillaHudElements.SCOREBOARD, ModOptions.INSTANCE.Scoreboard),
            Map.entry(VanillaHudElements.OVERLAY_MESSAGE, ModOptions.INSTANCE.ActionBar),
            Map.entry(VanillaHudElements.TITLE_AND_SUBTITLE, ModOptions.INSTANCE.ScreenTitle),
            Map.entry(VanillaHudElements.PLAYER_LIST, ModOptions.INSTANCE.PlayerList)
        ).forEach(
            (key, value) -> HudElementRegistry.replaceElement(
                key, original -> (graphics, ignore) -> {
                    if (value.apply(graphics.pose()))
                        original.render(graphics, ignore);
                    if (key == VanillaHudElements.HOTBAR)
                        graphics.pose().popMatrix(); // Patch #13
                    else HudManager.reset(graphics.pose());
                }
            )
        );
    }

    @Override public void onInitializeClient() {
        ModOptions.CURRENT_CONFIG = FabricLoader.getInstance().getConfigDir().resolve("hudmanager.json");
        ModOptions.load(); ModOptions.save(); modifyElement();
    }
}