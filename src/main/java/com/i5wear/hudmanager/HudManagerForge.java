package com.i5wear.hudmanager;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = "hudmanager")
public class HudManagerForge {

    public HudManagerForge(ModContainer instance) {
        instance.registerConfig(ModConfig.Type.CLIENT, HudManager.SPEC);
        instance.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

}