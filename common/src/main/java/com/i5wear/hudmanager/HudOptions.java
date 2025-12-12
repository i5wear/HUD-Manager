package com.i5wear.hudmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class HudOptions {

    public static File FILE = null; // Initialized by Fabric/NeoForge.
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
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
    public final HudManager ToastMessage = new HudManager();
    public final HudManager Tooltip = new HudManager();

    public static void load() {
        try (var Reader = new FileReader(FILE)) { INSTANCE = GSON.fromJson(Reader, HudOptions.class); }
        catch (Exception e) { LoggerFactory.getLogger(HudOptions.class).warn(e.getMessage()); }
    }

    public static void save() {
        try (var Writer = new FileWriter(FILE)) { GSON.toJson(INSTANCE, Writer); }
        catch (Exception e) { LoggerFactory.getLogger(HudOptions.class).warn(e.getMessage()); }
    }
}