package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.Manager;
import com.i5wear.hudmanager.config.ConfigScreen;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.v5.client.ConfigScreenFactoryRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.config.ModConfig;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class Main implements ClientModInitializer {

    private static final Map<ResourceLocation, Manager> CATEGORY = Map.ofEntries(
        Map.entry(VanillaHudElements.CROSSHAIR, Manager.CROSSHAIR),
        Map.entry(VanillaHudElements.SPECTATOR_MENU, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.HOTBAR, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.ARMOR_BAR, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.HEALTH_BAR, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.FOOD_BAR, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.AIR_BAR, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.MOUNT_HEALTH, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.INFO_BAR, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.EXPERIENCE_LEVEL, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.HELD_ITEM_TOOLTIP, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.SPECTATOR_TOOLTIP, Manager.HOTBAR_GROUP),
        Map.entry(VanillaHudElements.STATUS_EFFECTS, Manager.STATUS_EFFECT),
        Map.entry(VanillaHudElements.SCOREBOARD, Manager.SCOREBOARD_SIDEBAR),
        Map.entry(VanillaHudElements.OVERLAY_MESSAGE, Manager.ACTION_BAR),
        Map.entry(VanillaHudElements.TITLE_AND_SUBTITLE, Manager.SCREEN_TITLE),
        Map.entry(VanillaHudElements.PLAYER_LIST, Manager.PLAYER_LIST)
    );

    private static void modifyElement() {
        CATEGORY.forEach(
            (key, value) -> HudElementRegistry.replaceElement(
                key, original -> (graphics, tracker) -> {
                    if (value.apply(graphics))
                        original.render(graphics, tracker);
                    if (key == VanillaHudElements.HOTBAR) // Patch
                        graphics.pose().popMatrix();
                    else Manager.reset(graphics);
                }
            )
        );
    }

    @Override public void onInitializeClient() {
        ConfigRegistry.INSTANCE.register(Manager.IDENTITY, ModConfig.Type.CLIENT, Manager.CONFIG);
        ConfigScreenFactoryRegistry.INSTANCE.register(Manager.IDENTITY, (ignored, parent) -> new ConfigScreen(parent));
        modifyElement();
    }
}