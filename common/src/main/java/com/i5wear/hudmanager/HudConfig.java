package com.i5wear.hudmanager;

import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class HudConfig {

    public static HudConfig INSTANCE = new HudConfig();

    public final HudManager ActionBar = new HudManager();
    public final HudManager BossBar = new HudManager();
    public final HudManager ClosedCaption = new HudManager();
    public final HudManager Crosshair = new HudManager();
    public final HudManager DebugScreen = new HudManager();
    public final HudManager HotbarGroup = new HudManager();
    public final HudManager PlayerList = new HudManager();
    public final HudManager Scoreboard = new HudManager();
    public final HudManager ScreenTitle = new HudManager();
    public final HudManager StatusEffect = new HudManager();
    public final HudManager Toast = new HudManager();
    public final HudManager Tooltip = new HudManager();

    private HudConfig() {} // Singleton Pattern

    public static void load(File Config) {
        var Gson = new GsonBuilder().setPrettyPrinting().create();
        try (var Reader = new FileReader(Config)) { INSTANCE = Gson.fromJson(Reader, HudConfig.class); }
        catch (Exception err) { LoggerFactory.getLogger("hudmanager").warn(err.getMessage()); }
    }

    public static void save(File Config) {
        var Gson = new GsonBuilder().setPrettyPrinting().create();
        try (var Writer = new FileWriter(Config)) { Gson.toJson(INSTANCE, Writer); }
        catch (Exception err) { LoggerFactory.getLogger("hudmanager").warn(err.getMessage()); }
    }
}