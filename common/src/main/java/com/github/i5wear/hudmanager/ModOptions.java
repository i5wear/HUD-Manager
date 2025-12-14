package com.github.i5wear.hudmanager;

import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;

public class ModOptions {

    public static Path CURRENT_CONFIG = null; // From Loader
    public static ModOptions INSTANCE = new ModOptions();

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
        var Gson = new GsonBuilder().setPrettyPrinting().create();
        try { INSTANCE = Gson.fromJson(Files.readString(CURRENT_CONFIG), ModOptions.class); }
        catch (Exception e) { LoggerFactory.getLogger(ModOptions.class).warn(e.getMessage()); }
    }

    public static void save() {
        var Gson = new GsonBuilder().setPrettyPrinting().create();
        try { Files.writeString(CURRENT_CONFIG, Gson.toJson(INSTANCE, ModOptions.class)); }
        catch (Exception e) { LoggerFactory.getLogger(ModOptions.class).warn(e.getMessage()); }
    }
}