package com.i5wear.hudmanager;

import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class HudOptions {

    public static File CONFIG = null; // FROM LOADER
    public static HudOptions INSTANCE = new HudOptions();

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

    public static void load() {
        var Gson = new GsonBuilder().setPrettyPrinting().create();
        try (var Reader = new FileReader(CONFIG)) { INSTANCE = Gson.fromJson(Reader, HudOptions.class); }
        catch (Exception e) { LoggerFactory.getLogger("hudmanager").warn(e.getMessage()); }
    }

    public static void save() {
        var Gson = new GsonBuilder().setPrettyPrinting().create();
        try (var Writer = new FileWriter(CONFIG)) { Gson.toJson(INSTANCE, Writer); }
        catch (Exception e) { LoggerFactory.getLogger("hudmanager").warn(e.getMessage()); }
    }
}