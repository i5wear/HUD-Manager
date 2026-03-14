package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.Manager;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public final class ClientEntry implements ClientModInitializer {

    @Override public void onInitializeClient() {
        NeoForgeConfigRegistry.INSTANCE.register(Manager.IDENTITY, ModConfig.Type.CLIENT, Manager.CONFIG);
        ConfigScreenFactoryRegistry.INSTANCE.register(Manager.IDENTITY, ConfigurationScreen::new);
    }
}