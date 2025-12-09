package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.HudConfig;
import com.i5wear.hudmanager.HudManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class Main implements ClientModInitializer {

    private static final Map<ResourceLocation, HudManager> CATEGORY = Map.ofEntries(
        Map.entry(VanillaHudElements.CROSSHAIR, HudConfig.INSTANCE.Crosshair),
        Map.entry(VanillaHudElements.SPECTATOR_MENU, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.HOTBAR, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.ARMOR_BAR, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.HEALTH_BAR, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.FOOD_BAR, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.AIR_BAR, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.MOUNT_HEALTH, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.INFO_BAR, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.EXPERIENCE_LEVEL, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.HELD_ITEM_TOOLTIP, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.SPECTATOR_TOOLTIP, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaHudElements.STATUS_EFFECTS, HudConfig.INSTANCE.StatusEffect),
        Map.entry(VanillaHudElements.SCOREBOARD, HudConfig.INSTANCE.Scoreboard),
        Map.entry(VanillaHudElements.OVERLAY_MESSAGE, HudConfig.INSTANCE.ActionBar),
        Map.entry(VanillaHudElements.TITLE_AND_SUBTITLE, HudConfig.INSTANCE.ScreenTitle),
        Map.entry(VanillaHudElements.PLAYER_LIST, HudConfig.INSTANCE.PlayerList)
    );

    private static void modifyElement() {
        CATEGORY.forEach(
            (key, value) -> HudElementRegistry.replaceElement(
                key, original -> (graphics, tracker) -> {
                    if (value.apply(graphics))
                        original.render(graphics, tracker);
                    if (key == VanillaHudElements.HOTBAR) // Patch
                        graphics.pose().popMatrix();
                    else HudManager.reset(graphics);
                }
            )
        );
    }

    @Override public void onInitializeClient() {
        HudConfig.load(FabricLoader.getInstance().getConfigDir().resolve("hudmanager.json").toFile());
        HudConfig.save(FabricLoader.getInstance().getConfigDir().resolve("hudmanager.json").toFile());
        modifyElement();
    }
}