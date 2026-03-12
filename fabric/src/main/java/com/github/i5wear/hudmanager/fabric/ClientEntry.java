package com.github.i5wear.hudmanager.fabric;

import com.github.i5wear.hudmanager.HudManager;
import com.github.i5wear.hudmanager.ModOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.loader.api.FabricLoader;

import java.util.Map;
import java.util.stream.Stream;

public final class ClientEntry implements ClientModInitializer {

    private static void modifyElement() {
        Stream.of(
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
            Map.entry(VanillaHudElements.MOB_EFFECTS, ModOptions.INSTANCE.StatusEffect),
            Map.entry(VanillaHudElements.SCOREBOARD, ModOptions.INSTANCE.Scoreboard),
            Map.entry(VanillaHudElements.OVERLAY_MESSAGE, ModOptions.INSTANCE.ActionBar),
            Map.entry(VanillaHudElements.TITLE_AND_SUBTITLE, ModOptions.INSTANCE.ScreenTitle),
            Map.entry(VanillaHudElements.PLAYER_LIST, ModOptions.INSTANCE.PlayerList)
        ).forEach(
            entry -> HudElementRegistry.replaceElement(
                entry.getKey(), original -> (graphics, tracker) -> {
                    HudManager.CURRENT = entry.getValue();
                    if (HudManager.CURRENT.Display)
                        original.extractRenderState(graphics, tracker);
                    if (entry.getKey() != VanillaHudElements.HOTBAR)
                        HudManager.CURRENT = HudManager.DEFAULT; // Patch #13
                }
            )
        );
    }

    @Override public void onInitializeClient() {
        ModOptions.DIRECTORY = FabricLoader.getInstance().getConfigDir();
        ModOptions.load(); ModOptions.save(); modifyElement();
    }
}