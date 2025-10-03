package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.Config;
import com.i5wear.hudmanager.Global;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.v5.client.ConfigScreenFactoryRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

import java.lang.reflect.Field;

public class Main implements ClientModInitializer {
    
    private static void modifyElement(ResourceLocation Name) {
        HudElementRegistry.replaceElement(Name,
                original -> (arg0, arg1) -> {
                    Config Value = Config.get(Name);
                    if (Value == null) original.render(arg0, arg1);
                    else if (Value.Size.get() > 0 && Value.Show.get()) {
                        Global.CURRENT_SIZE = Value.Size.get();
                        arg0.pose().pushMatrix();
                        arg0.pose().scale(0.01f * Value.Size.get());
                        arg0.pose().translate(Value.PosX.get(), Value.PosY.get());
                        original.render(arg0, arg1);
                        arg0.pose().popMatrix();
                        Global.CURRENT_SIZE = 100;
                    }
                }
        );
    }

    @Override public void onInitializeClient() {
        ConfigRegistry.INSTANCE.register(Global.MOD_ID, ModConfig.Type.CLIENT, Config.SPEC);
        ConfigScreenFactoryRegistry.INSTANCE.register(Global.MOD_ID, (parent, screen) -> new ConfigurationScreen(Global.MOD_ID, screen));
        for (Field Element : VanillaHudElements.class.getDeclaredFields()) {
            try { modifyElement((ResourceLocation) Element.get(null)); }
            catch (IllegalAccessException ignored) {}
        }
    }
}