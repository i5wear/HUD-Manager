package com.i5wear.hudmanager;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.v5.client.ConfigScreenFactoryRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public class HudManagerFabric implements ClientModInitializer {

    @Override public void onInitializeClient() {
        ConfigRegistry.INSTANCE.register("hudmanager", ModConfig.Type.CLIENT, HudManager.SPEC);
        ConfigScreenFactoryRegistry.INSTANCE.register("hudmanager", (parentScreen, screenConsumer) -> new ConfigurationScreen("hudmanager", screenConsumer) );
    }

}