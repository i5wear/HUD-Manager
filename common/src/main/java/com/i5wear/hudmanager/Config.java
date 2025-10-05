package com.i5wear.hudmanager;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    public ModConfigSpec.BooleanValue Show;
    public ModConfigSpec.IntValue Size;
    public ModConfigSpec.IntValue PosX;
    public ModConfigSpec.IntValue PosY;

    public static final String MOD_ID = "hudmanager";
    public static volatile Integer CURRENT_SIZE = 100;

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final Config ACTION_BAR = new Config("Action_Bar");
    public static final Config BOSS_BAR = new Config("Boss_Bar");
    public static final Config CLOSED_CAPTION = new Config("Closed_Caption");
    public static final Config CROSSHAIR = new Config("Crosshair");
    public static final Config DEBUG_SCREEN = new Config("Debug_Screen");
    public static final Config HOTBAR_GROUP = new Config("Hotbar_Group");
    public static final Config PLAYER_LIST = new Config("Player_List");
    public static final Config SCOREBOARD_SIDEBAR = new Config("Scoreboard_Sidebar");
    public static final Config SCREEN_TITLE = new Config("Screen_Title");
    public static final Config STATUS_EFFECT = new Config("Status_Effect");
    public static final Config TOAST = new Config("Toast");
    // public static final Config TOOLTIP = new Config("Tooltip");
    public static final ModConfigSpec SPEC = BUILDER.build();

    private Config(String Name) {
        Show = BUILDER.define(Name + ".Show", true);
        Size = BUILDER.defineInRange(Name + ".Size", 100, 0, 200);
        PosX = BUILDER.defineInRange(Name + ".PosX", 0, -100, +100);
        PosY = BUILDER.defineInRange(Name + ".PosY", 0, -100, +100);
    }
}