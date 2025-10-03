package com.i5wear.hudmanager.neoforge;

import com.i5wear.hudmanager.Config;
import com.i5wear.hudmanager.Global;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = Global.MOD_ID, dist = Dist.CLIENT)
public class Main {

    public Main(ModContainer instance) {
        instance.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        instance.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        NeoForge.EVENT_BUS.register(Main.class);
    }

    @SubscribeEvent private static void beforeElementRender(RenderGuiLayerEvent.Pre event) {
        Config Value = Config.get(event.getName());
        if (Value == null) event.getGuiGraphics().pose().pushMatrix();
        else if (Value.Size.get() == 0 || !Value.Show.get()) event.setCanceled(true);
        else {
            Global.CURRENT_SIZE = Value.Size.get();
            event.getGuiGraphics().pose().pushMatrix();
            event.getGuiGraphics().pose().scale(0.01f * Value.Size.get());
            event.getGuiGraphics().pose().translate(Value.PosX.get(), Value.PosY.get());
        }
    }

    @SubscribeEvent private static void afterElementRender(RenderGuiLayerEvent.Post event) {
        event.getGuiGraphics().pose().popMatrix();
        Global.CURRENT_SIZE = 100;
    }
}