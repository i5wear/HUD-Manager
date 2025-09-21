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

    public static class Config {

        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static final ModConfigSpec.IntValue
                ActionBarScale = BUILDER.comment("//hudmanager.config.ActionBarScale.tooltip").defineInRange("ActionBarScale", 100, 0, 200),
                AutoSaveIndicatorScale = BUILDER.comment("//hudmanager.config.AutoSaveIndicatorScale.tooltip").defineInRange("AutoSaveIndicatorScale", 100, 0, 200),
                BossBarScale = BUILDER.comment("//hudmanager.config.BossBarScale.tooltip").defineInRange("BossBarScale", 100, 0, 200),
                ClosedCaptionScale = BUILDER.comment("//hudmanager.config.ClosedCaptionScale.tooltip").defineInRange("ClosedCaptionScale", 100, 0, 200),
                CrosshairScale = BUILDER.comment("//hudmanager.config.CrosshairScale.tooltip").defineInRange("CrosshairScale", 100, 0, 200),
                DebugScreenScale = BUILDER.comment("//hudmanager.config.DebugScreenScale.tooltip").defineInRange("DebugScreenScale", 100, 0, 200),
                PlayerListScale = BUILDER.comment("//hudmanager.config.PlayerListScale.tooltip").defineInRange("PlayerListScale", 100, 0, 200),
                ScoreboardSidebarScale = BUILDER.comment("//hudmanager.config.ScoreboardSidebarScale.tooltip").defineInRange("ScoreboardSidebarScale", 100, 0, 200),
                ScreenTitleScale = BUILDER.comment("//hudmanager.config.ScreenTitleScale.tooltip").defineInRange("ScreenTitleScale", 100, 0, 200),
                StatusEffectScale = BUILDER.comment("//hudmanager.config.StatusEffectScale.tooltip").defineInRange("StatusEffectScale", 100, 0, 200);

        private static final ModConfigSpec SPEC = BUILDER.build();

    }

    public HUDManager(ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, HUDManager.Config.SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

}