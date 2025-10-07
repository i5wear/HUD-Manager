package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.Config;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class Main implements ClientModInitializer {

    private static final Map<ResourceLocation, Config> Category = Map.ofEntries(
            Map.entry(IdentifiedLayer.CROSSHAIR, Config.CROSSHAIR),
            Map.entry(IdentifiedLayer.HOTBAR_AND_BARS, Config.HOTBAR_GROUP),
            Map.entry(IdentifiedLayer.EXPERIENCE_LEVEL, Config.HOTBAR_GROUP),
            Map.entry(IdentifiedLayer.STATUS_EFFECTS, Config.STATUS_EFFECT),
            Map.entry(IdentifiedLayer.BOSS_BAR, Config.BOSS_BAR),
            Map.entry(IdentifiedLayer.SCOREBOARD, Config.SCOREBOARD_SIDEBAR),
            Map.entry(IdentifiedLayer.OVERLAY_MESSAGE, Config.ACTION_BAR),
            Map.entry(IdentifiedLayer.TITLE_AND_SUBTITLE, Config.SCREEN_TITLE),
            Map.entry(IdentifiedLayer.PLAYER_LIST, Config.PLAYER_LIST),
            Map.entry(IdentifiedLayer.SUBTITLES, Config.CLOSED_CAPTION)
    );
    
    private static void modifyElement() {
        for (var Element : Category.entrySet()) {
            HudLayerRegistrationCallback.EVENT.register(
                wrapper -> wrapper.replaceLayer(
                    Element.getKey(), original -> IdentifiedLayer.of(
                        Element.getKey(), (instance, delta) -> {
                            Config Value = Element.getValue();
                            if (Value.Show.get() && Value.Size.get() > 0) {
                                Config.CURRENT_SIZE = Value.Size.get();
                                instance.pose().pushPose();
                                instance.pose().scale(0.01f * Value.Size.get(), 0.01f * Value.Size.get(), 1);
                                instance.pose().translate(0.01f * Value.PosX.get() * instance.guiWidth(), 0.01f * Value.PosY.get() * instance.guiHeight(), 0);
                                original.render(instance, delta);
                                instance.pose().popPose();
                                Config.CURRENT_SIZE = 100;
                            }
                        }
                    )
                )
            );
        }
    }

    @Override public void onInitializeClient() {
        NeoForgeConfigRegistry.INSTANCE.register(Config.MOD_ID, ModConfig.Type.CLIENT, Config.SPEC);
        ConfigScreenFactoryRegistry.INSTANCE.register(Config.MOD_ID, (parent, screen) -> new ConfigurationScreen(Config.MOD_ID, screen));
        Main.modifyElement();
    }
}