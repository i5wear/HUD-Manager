package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.Manager;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.v5.client.ConfigScreenFactoryRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

import java.util.Map;
import java.util.stream.Stream;

public final class ClientEntry implements ClientModInitializer {

    private static void modifyElement() {
        Stream.of(
            Map.entry(IdentifiedLayer.CROSSHAIR, Manager.CROSSHAIR),
            Map.entry(IdentifiedLayer.HOTBAR_AND_BARS, Manager.HOTBAR_GROUP),
            Map.entry(IdentifiedLayer.EXPERIENCE_LEVEL, Manager.HOTBAR_GROUP),
            Map.entry(IdentifiedLayer.STATUS_EFFECTS, Manager.STATUS_EFFECT),
            Map.entry(IdentifiedLayer.BOSS_BAR, Manager.BOSS_BAR),
            Map.entry(IdentifiedLayer.DEBUG, Manager.DEBUG_SCREEN),
            Map.entry(IdentifiedLayer.SCOREBOARD, Manager.SCOREBOARD),
            Map.entry(IdentifiedLayer.OVERLAY_MESSAGE, Manager.ACTION_BAR),
            Map.entry(IdentifiedLayer.TITLE_AND_SUBTITLE, Manager.SCREEN_TITLE),
            Map.entry(IdentifiedLayer.PLAYER_LIST, Manager.PLAYER_LIST),
            Map.entry(IdentifiedLayer.SUBTITLES, Manager.CLOSED_CAPTION)
        ).forEach(
            entry -> HudLayerRegistrationCallback.EVENT.register(
                wrapper -> wrapper.replaceLayer(
                    entry.getKey(), original -> IdentifiedLayer.of(
                        original.id(), (graphics, tracker) -> {
                            if (entry.getValue().apply(graphics))
                                original.render(graphics, tracker);
                            Manager.reset(graphics);
                        }
                    )
                )
            )
        );
    }

    @Override public void onInitializeClient() {
        ConfigRegistry.INSTANCE.register(Manager.IDENTITY, ModConfig.Type.CLIENT, Manager.CONFIG);
        ConfigScreenFactoryRegistry.INSTANCE.register(Manager.IDENTITY, ConfigurationScreen::new);
        modifyElement();
    }
}