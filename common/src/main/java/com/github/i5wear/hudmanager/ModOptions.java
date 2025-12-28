package com.github.i5wear.hudmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * <p> A simple config implementation. </p>
 * <p> Contains arbitrary objects that convert to, and get values from JSON or {@link ModOptionsScreen}. </p>
 * <p> Supports {@link Number}, {@link String}, {@link Boolean} and {@link Enum} types, or their collections and combinations. </p>
 * <p> For special cases, register your own type adapter at {@link #ADAPTER} and it works for {@link ModOptionsScreen}. </p>
 * <p> Requires runtime initialization of the config path, before calling method {@link #load()} and {@link #save()}. </p>
 *
 * @see ModOptionsScreen
 * @author i5wear
 */
public class ModOptions {

    public static final Gson ADAPTER = new GsonBuilder().setLenient().create();
    public static final Gson PRINTER = new GsonBuilder().setPrettyPrinting().create();

    public static Path CURRENT_CONFIG = null;
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
        try { INSTANCE = ADAPTER.fromJson(Files.readString(CURRENT_CONFIG), ModOptions.class); }
        catch (Exception ignore) { INSTANCE = new ModOptions(); }
    }

    public static void save() {
        try { Files.writeString(CURRENT_CONFIG, PRINTER.toJson(ADAPTER.toJsonTree(INSTANCE))); }
        catch (Exception ignore) { INSTANCE = new ModOptions(); }
    }
}