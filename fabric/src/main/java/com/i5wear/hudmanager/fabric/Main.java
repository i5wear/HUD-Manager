package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.Manager;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.v5.client.ConfigScreenFactoryRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

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
            Map.entry(VanillaHudElements.BOSS_BAR, Manager.BOSS_BAR),
            Map.entry(VanillaHudElements.SCOREBOARD, Manager.SCOREBOARD_SIDEBAR),
            Map.entry(VanillaHudElements.OVERLAY_MESSAGE, Manager.ACTION_BAR),
            Map.entry(VanillaHudElements.TITLE_AND_SUBTITLE, Manager.SCREEN_TITLE),
            Map.entry(VanillaHudElements.PLAYER_LIST, Manager.PLAYER_LIST),
            Map.entry(VanillaHudElements.SUBTITLES, Manager.CLOSED_CAPTION)
    );
    
    private static void modifyElement() {
        for (var Element : CATEGORY.entrySet()) {
            HudElementRegistry.replaceElement(
                Element.getKey(), Original -> (Instance, DeltaTick) -> {
                    if (Element.getValue().apply(Instance))
                        Original.render(Instance, DeltaTick);
                    Instance.pose().popMatrix();
                    if (Element.getKey() != VanillaHudElements.HOTBAR) // Patch
                        Manager.CURRENT_SIZE = 100;
                }
            );
        }
    }

    @Override public void onInitializeClient() {
        ConfigRegistry.INSTANCE.register(Manager.MOD_ID, ModConfig.Type.CLIENT, Manager.CONFIG_SPEC);
        ConfigScreenFactoryRegistry.INSTANCE.register(Manager.MOD_ID, (Parent, Screen) -> new ConfigurationScreen(Manager.MOD_ID, Screen));
        Main.modifyElement();
    }
}