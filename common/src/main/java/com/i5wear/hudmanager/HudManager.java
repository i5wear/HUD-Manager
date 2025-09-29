package com.i5wear.hudmanager;

import net.neoforged.neoforge.common.ModConfigSpec;

public class HudManager {

    public static final String MOD_ID = "hudmanager";
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static ModConfigSpec.BooleanValue ActionBarShow = BUILDER.define("ActionBar.show", true);
    public static ModConfigSpec.IntValue ActionBarScale = BUILDER.defineInRange("ActionBar.scale", 100, 0, 200);
    public static ModConfigSpec.IntValue ActionBarOffsetX = BUILDER.defineInRange("ActionBar.offsetx", 0, -100, 100);
    public static ModConfigSpec.IntValue ActionBarOffsetY = BUILDER.defineInRange("ActionBar.offsety", 0, -100, 100);

    public static ModConfigSpec.BooleanValue AutoSaveIndicatorShow = BUILDER.define("AutoSaveIndicator.show", true);
    public static ModConfigSpec.IntValue AutoSaveIndicatorScale = BUILDER.defineInRange("AutoSaveIndicator.scale", 100, 0, 200);
    public static ModConfigSpec.IntValue AutoSaveIndicatorOffsetX = BUILDER.defineInRange("AutoSaveIndicator.offsetx", 0, -100, 100);
    public static ModConfigSpec.IntValue AutoSaveIndicatorOffsetY = BUILDER.defineInRange("AutoSaveIndicator.offsety", 0, -100, 100);

    public static ModConfigSpec.BooleanValue BossBarShow = BUILDER.define("BossBar.show", true);
    public static ModConfigSpec.IntValue BossBarScale = BUILDER.defineInRange("BossBar.scale", 100, 0, 200);
    public static ModConfigSpec.IntValue BossBarOffsetX = BUILDER.defineInRange("BossBar.offsetx", 0, -100, 100);
    public static ModConfigSpec.IntValue BossBarOffsetY = BUILDER.defineInRange("BossBar.offsety", 0, -100, 100);

    public static ModConfigSpec.BooleanValue ClosedCaptionShow = BUILDER.define("ClosedCaption.show", true);
    public static ModConfigSpec.IntValue ClosedCaptionScale = BUILDER.defineInRange("ClosedCaption.scale", 100, 0, 200);
    public static ModConfigSpec.IntValue ClosedCaptionOffsetX = BUILDER.defineInRange("ClosedCaption.offsetx", 0, -100, 100);
    public static ModConfigSpec.IntValue ClosedCaptionOffsetY = BUILDER.defineInRange("ClosedCaption.offsety", 0, -100, 100);

    public static ModConfigSpec.BooleanValue CrosshairShow = BUILDER.define("Crosshair.show", true);
    public static ModConfigSpec.IntValue CrosshairScale = BUILDER.defineInRange("Crosshair.scale", 100, 0, 200);
    public static ModConfigSpec.IntValue CrosshairOffsetX = BUILDER.defineInRange("Crosshair.offsetx", 0, -100, 100);
    public static ModConfigSpec.IntValue CrosshairOffsetY = BUILDER.defineInRange("Crosshair.offsety", 0, -100, 100);

    public static ModConfigSpec.BooleanValue DebugScreenShow = BUILDER.define("DebugScreen.show", true);
    public static ModConfigSpec.IntValue DebugScreenScale = BUILDER.defineInRange("DebugScreen.scale", 100, 0, 200);
    public static ModConfigSpec.IntValue DebugScreenOffsetX = BUILDER.defineInRange("DebugScreen.offsetx", 0, -100, 100);
    public static ModConfigSpec.IntValue DebugScreenOffsetY = BUILDER.defineInRange("DebugScreen.offsety", 0, -100, 100);

    public static ModConfigSpec.BooleanValue PlayerListShow = BUILDER.define("PlayerList.show", true);
    public static ModConfigSpec.IntValue PlayerListScale = BUILDER.defineInRange("PlayerList.scale", 100, 0, 200);
    public static ModConfigSpec.IntValue PlayerListOffsetX = BUILDER.defineInRange("PlayerList.offsetx", 0, -100, 100);
    public static ModConfigSpec.IntValue PlayerListOffsetY = BUILDER.defineInRange("PlayerList.offsety", 0, -100, 100);

    public static ModConfigSpec.BooleanValue ScoreboardSidebarShow = BUILDER.define("ScoreboardSidebar.show", true);
    public static ModConfigSpec.IntValue ScoreboardSidebarScale = BUILDER.defineInRange("ScoreboardSidebar.scale", 100, 0, 200);
    public static ModConfigSpec.IntValue ScoreboardSidebarOffsetX = BUILDER.defineInRange("ScoreboardSidebar.offsetx", 0, -100, 100);
    public static ModConfigSpec.IntValue ScoreboardSidebarOffsetY = BUILDER.defineInRange("ScoreboardSidebar.offsety", 0, -100, 100);

    public static ModConfigSpec.BooleanValue ScreenTitleShow = BUILDER.define("ScreenTitle.show", true);
    public static ModConfigSpec.IntValue ScreenTitleScale = BUILDER.defineInRange("ScreenTitle.scale", 100, 0, 200);
    public static ModConfigSpec.IntValue ScreenTitleOffsetX = BUILDER.defineInRange("ScreenTitle.offsetx", 0, -100, 100);
    public static ModConfigSpec.IntValue ScreenTitleOffsetY = BUILDER.defineInRange("ScreenTitle.offsety", 0, -100, 100);

    public static ModConfigSpec.BooleanValue StatusEffectShow = BUILDER.define("StatusEffect.show", true);
    public static ModConfigSpec.IntValue StatusEffectScale = BUILDER.defineInRange("StatusEffect.scale", 100, 0, 200);
    public static ModConfigSpec.IntValue StatusEffectOffsetX = BUILDER.defineInRange("StatusEffect.offsetx", 0, -100, 100);
    public static ModConfigSpec.IntValue StatusEffectOffsetY = BUILDER.defineInRange("StatusEffect.offsety", 0, -100, 100);

    public static final ModConfigSpec SPEC = BUILDER.build();

    // Global Variables that are used by mixins.
    public static class Temp {
        public static int SCALE = 100;
    }
}