package com.i5wear.hudmanager;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.ModConfigSpec;

@Mod(value = "hudmanager")
public class HUDManager {

    public static float SCALE = 1;

    public static class Configuration {

        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static final ModConfigSpec.IntValue
                ActionBarScale = BUILDER.defineInRange("ActionBarScale", 100, 0, 200),
                AutoSaveIndicatorScale = BUILDER.defineInRange("AutoSaveIndicatorScale", 100, 0, 200),
                BossBarScale = BUILDER.defineInRange("BossBarScale", 100, 0, 200),
                ClosedCaptionScale = BUILDER.defineInRange("ClosedCaptionScale", 100, 0, 200),
                CrosshairScale = BUILDER.defineInRange("CrosshairScale", 100, 0, 200),
                DebugScreenScale = BUILDER.defineInRange("DebugScreenScale", 100, 0, 200),
                PlayerListScale = BUILDER.defineInRange("PlayerListScale", 100, 0, 200),
                ScoreboardSidebarScale = BUILDER.defineInRange("ScoreboardSidebarScale", 100, 0, 200),
                ScreenTitleScale = BUILDER.defineInRange("ScreenTitleScale", 100, 0, 200),
                StatusEffectScale = BUILDER.defineInRange("StatusEffectScale", 100, 0, 200);

        private static final ModConfigSpec SPEC = BUILDER.build();

    }

    public HUDManager(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, HUDManager.Configuration.SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

}