package com.i5wear.hudmanager;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.LoggerFactory;

import java.io.File;

public class HudOptions {

    public static volatile File CONFIG = null; // Initialized by Fabric/NeoForge.
    public static final JsonMapper MAPPER = new JsonMapper();
    public static final HudOptions INSTANCE = new HudOptions();

    @JsonMerge public final HudManager ActionBar = new HudManager();
    @JsonMerge public final HudManager BossBar = new HudManager();
    @JsonMerge public final HudManager ClosedCaption = new HudManager();
    @JsonMerge public final HudManager Crosshair = new HudManager();
    @JsonMerge public final HudManager DebugScreen = new HudManager();
    @JsonMerge public final HudManager HotbarGroup = new HudManager();
    @JsonMerge public final HudManager PlayerList = new HudManager();
    @JsonMerge public final HudManager Scoreboard = new HudManager();
    @JsonMerge public final HudManager ScreenTitle = new HudManager();
    @JsonMerge public final HudManager StatusEffect = new HudManager();
    @JsonMerge public final HudManager ToastMessage = new HudManager();
    @JsonMerge public final HudManager Tooltip = new HudManager();

    public static void load() {
        try { MAPPER.readerForUpdating(INSTANCE).readValue(CONFIG); }
        catch (Exception e) { LoggerFactory.getLogger(HudOptions.class).warn(e.getMessage()); }
    }

    public static void save() {
        try { MAPPER.writerWithDefaultPrettyPrinter().writeValue(CONFIG, INSTANCE); }
        catch (Exception e) { LoggerFactory.getLogger(HudOptions.class).warn(e.getMessage()); }
    }
}