package com.i5wear.hudmanager.neoforge;

import com.i5wear.hudmanager.Manager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Manager.IDENTITY, dist = Dist.CLIENT)
public final class ClientEntry {

    public ClientEntry(ModContainer loader) {
        loader.registerConfig(ModConfig.Type.CLIENT, Manager.CONFIG);
        loader.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}