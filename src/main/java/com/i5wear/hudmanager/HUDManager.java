package com.i5wear.hudmanager;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class HUDManager implements ClientModInitializer, ModMenuApi {

    public static float SCALE = 1;
    public static Configuration CONFIGURATION;

    @Config(name = "hudmanager") public static class Configuration implements ConfigData {

        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public int ActionBarScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public int AutoSaveIndicatorScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public int BossBarScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public int ClosedCaptionScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public int CrosshairScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public int DebugScreenScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public int PlayerListScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public int ScoreboardSidebarScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public int ScreenTitleScale = 100;
        @ConfigEntry.Gui.Tooltip @ConfigEntry.BoundedDiscrete(min = 0, max = 200) public int StatusEffectScale = 100;

    }

    @Override public void onInitializeClient() {
        AutoConfig.register(Configuration.class, Toml4jConfigSerializer::new);
        CONFIGURATION = AutoConfig.getConfigHolder(Configuration.class).getConfig();
    }

    @Override public ConfigScreenFactory<?> getModConfigScreenFactory()
    { return parent -> AutoConfig.getConfigScreen(Configuration.class, parent).get(); }

}