package com.i5wear.hudmanager;

import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class HudConfig {

    public static File CONFIG = null; // LOADER SUPPORT
    public static HudConfig INSTANCE = new HudConfig();

    public final HudManager ActionBar = new HudManager("ActionBar");
    public final HudManager BossBar = new HudManager("BossBar");
    public final HudManager ClosedCaption = new HudManager("ClosedCaption");
    public final HudManager Crosshair = new HudManager("Crosshair");
    public final HudManager DebugScreen = new HudManager("DebugScreen");
    public final HudManager HotbarGroup = new HudManager("HotbarGroup");
    public final HudManager PlayerList = new HudManager("PlayerList");
    public final HudManager Scoreboard = new HudManager("Scoreboard");
    public final HudManager ScreenTitle = new HudManager("ScreenTitle");
    public final HudManager StatusEffect = new HudManager("StatusEffect");
    public final HudManager Toast = new HudManager("Toast");
    public final HudManager Tooltip = new HudManager("Tooltip");

    public static void load() {
        var Gson = new GsonBuilder().setPrettyPrinting().create();
        try (var Reader = new FileReader(CONFIG)) { INSTANCE = Gson.fromJson(Reader, HudConfig.class); }
        catch (Exception err) { LoggerFactory.getLogger("hudmanager").warn(err.getMessage()); }
    }

    public static void save() {
        var Gson = new GsonBuilder().setPrettyPrinting().create();
        try (var Writer = new FileWriter(CONFIG)) { Gson.toJson(INSTANCE, Writer); }
        catch (Exception err) { LoggerFactory.getLogger("hudmanager").warn(err.getMessage()); }
    }
}