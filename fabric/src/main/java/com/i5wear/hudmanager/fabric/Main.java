package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.Config;
import com.i5wear.hudmanager.Global;
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
    
    private static void modifyElement() {
        for (Map.Entry<ResourceLocation, Config> Element : Category.entrySet()) {
            HudElementRegistry.replaceElement(
                    Element.getKey(), original -> (arg0, arg1) -> {
                        Config Value = Element.getValue();
                        if (Value == null) original.render(arg0, arg1);
                        else if (Value.Size.get() > 0 && Value.Show.get()) {
                            Global.CURRENT_SIZE = Value.Size.get();
                            arg0.pose().pushMatrix();
                            arg0.pose().scale(0.01f * Value.Size.get());
                            arg0.pose().translate(0.01f * Value.PosX.get() * arg0.guiWidth(), 0.01f * Value.PosY.get() * arg0.guiHeight());
                            original.render(arg0, arg1);
                            arg0.pose().popMatrix();
                            Global.CURRENT_SIZE = 100;
                        }
                    }
            );
        }
    }

    @Override public void onInitializeClient() {
        ConfigRegistry.INSTANCE.register(Global.MOD_ID, ModConfig.Type.CLIENT, Config.SPEC);
        ConfigScreenFactoryRegistry.INSTANCE.register(Global.MOD_ID, (parent, screen) -> new ConfigurationScreen(Global.MOD_ID, screen));
        Main.modifyElement();
    }
}