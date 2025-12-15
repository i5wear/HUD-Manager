package com.github.i5wear.hudmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Path;

public class ModOptions {

    public static final Gson READER = new GsonBuilder().setLenient().create();
    public static final Gson WRITER = new GsonBuilder().setPrettyPrinting().create();

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
        try { INSTANCE = READER.fromJson(Files.readString(CURRENT_CONFIG), ModOptions.class); }
        catch (Exception ignore) { INSTANCE = new ModOptions(); }
    }

    public static void save() {
        try { Files.writeString(CURRENT_CONFIG, WRITER.toJson(INSTANCE, ModOptions.class)); }
        catch (Exception ignore) { INSTANCE = new ModOptions(); }
    }
}