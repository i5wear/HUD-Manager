package com.i5wear.hudmanager;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class HUDManager implements ClientModInitializer, ModMenuApi {

    public static float SCALE = 1;

	@Override public void onInitializeClient() { AutoConfig.register(Configuration.class, GsonConfigSerializer::new); }

    @Override public ConfigScreenFactory<?> getModConfigScreenFactory() { return parent -> AutoConfig.getConfigScreen(Configuration.class, parent).get(); }

    @Config(name = "hudmanager") public static class Configuration implements ConfigData {

        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public static int ActionBarScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public static int AutoSaveIndicatorScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public static int BossBarScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public static int ClosedCaptionScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public static int CrosshairScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public static int DebugScreenScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public static int PlayerListScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public static int ScoreboardSidebarScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public static int ScreenTitleScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public static int StatusEffectScale = 100;

    }

}