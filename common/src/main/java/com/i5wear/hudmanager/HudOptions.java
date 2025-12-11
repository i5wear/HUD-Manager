package com.i5wear.hudmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class HudOptions {

    public static File CONFIG = null; // FROM LOADER
    public static ObjectMapper MAPPER = new JsonMapper();
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
        try (var Reader = new FileReader(CONFIG)) { INSTANCE = MAPPER.readValue(Reader, HudOptions.class); }
        catch (Exception e) { LoggerFactory.getLogger("hudmanager").warn(e.getMessage()); }
    }

    public static void save() {
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        try (var Writer = new FileWriter(CONFIG)) { MAPPER.writeValue(Writer, INSTANCE); }
        catch (Exception e) { LoggerFactory.getLogger("hudmanager").warn(e.getMessage()); }
    }
}