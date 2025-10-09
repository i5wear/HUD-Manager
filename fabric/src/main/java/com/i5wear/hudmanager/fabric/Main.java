package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.Manager;
import fuzs.forgeconfigapiport.fabric.api.v5.client.ConfigScreenFactoryRegistry;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
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

    private static final Map<ResourceLocation, Manager> CATEGORY = Map.ofEntries(
            Map.entry(IdentifiedLayer.CROSSHAIR, Manager.CROSSHAIR),
            Map.entry(IdentifiedLayer.HOTBAR_AND_BARS, Manager.HOTBAR_GROUP),
            Map.entry(IdentifiedLayer.EXPERIENCE_LEVEL, Manager.HOTBAR_GROUP),
            Map.entry(IdentifiedLayer.STATUS_EFFECTS, Manager.STATUS_EFFECT),
            Map.entry(IdentifiedLayer.BOSS_BAR, Manager.BOSS_BAR),
            Map.entry(IdentifiedLayer.DEBUG, Manager.DEBUG_SCREEN),
            Map.entry(IdentifiedLayer.SCOREBOARD, Manager.SCOREBOARD_SIDEBAR),
            Map.entry(IdentifiedLayer.OVERLAY_MESSAGE, Manager.ACTION_BAR),
            Map.entry(IdentifiedLayer.TITLE_AND_SUBTITLE, Manager.SCREEN_TITLE),
            Map.entry(IdentifiedLayer.PLAYER_LIST, Manager.PLAYER_LIST),
            Map.entry(IdentifiedLayer.SUBTITLES, Manager.CLOSED_CAPTION)
    );
    
    private static void modifyElement() {
        for (var Element : Main.CATEGORY.entrySet()) {
            HudLayerRegistrationCallback.EVENT.register(
                Modifier -> Modifier.replaceLayer(
                    Element.getKey(), Original -> IdentifiedLayer.of(
                        Original.id(), (Instance, DeltaTick) -> {
                            if (Element.getValue().apply(Instance))
                                Original.render(Instance, DeltaTick);
                            Instance.pose().popPose();
                            Manager.CURRENT_SIZE = 100;
                        }
                    )
                )
            );
        }
    }

    @Override public void onInitializeClient() {
        ConfigRegistry.INSTANCE.register(Manager.MOD_ID, ModConfig.Type.CLIENT, Manager.CONFIG_SPEC);
        ConfigScreenFactoryRegistry.INSTANCE.register(Manager.MOD_ID, (parent, screen) -> new ConfigurationScreen(Manager.MOD_ID, screen));
        Main.modifyElement();
    }
}