package com.i5wear.hudmanager.neoforge;

import com.i5wear.hudmanager.HudManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = HudManager.MOD_ID, dist = Dist.CLIENT)
public class HudManagerNeoforge {

    public HudManagerNeoforge(ModContainer instance) {
        instance.registerConfig(ModConfig.Type.CLIENT, HudManager.SPEC);
        instance.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}