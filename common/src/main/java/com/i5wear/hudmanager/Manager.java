package com.i5wear.hudmanager;

import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Manager {

    public ModConfigSpec.BooleanValue Show;
    public ModConfigSpec.IntValue Scale;
    public ModConfigSpec.IntValue Alpha;
    public ModConfigSpec.IntValue PosX;
    public ModConfigSpec.IntValue PosY;

    public static final String IDENTITY = "hudmanager";
    public static volatile int CURRENT_SCALE = 100;
    public static volatile int CURRENT_ALPHA = 100;

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final Manager ACTION_BAR = new Manager("Action_Bar");
    public static final Manager BOSS_BAR = new Manager("Boss_Bar");
    public static final Manager CLOSED_CAPTION = new Manager("Closed_Caption");
    public static final Manager CROSSHAIR = new Manager("Crosshair");
    public static final Manager DEBUG_SCREEN = new Manager("Debug_Screen");
    public static final Manager HOTBAR_GROUP = new Manager("Hotbar_Group");
    public static final Manager PLAYER_LIST = new Manager("Player_List");
    public static final Manager SCOREBOARD_SIDEBAR = new Manager("Scoreboard_Sidebar");
    public static final Manager SCREEN_TITLE = new Manager("Screen_Title");
    public static final Manager STATUS_EFFECT = new Manager("Status_Effect");
    public static final Manager TOAST = new Manager("Toast");
    public static final Manager TOOLTIP = new Manager("Tooltip");
    public static final ModConfigSpec CONFIG = BUILDER.build();

    private Manager(String Name) {
        Show = BUILDER.define(Name + ".Show", true);
        Scale = BUILDER.defineInRange(Name + ".Scale", 100, 0, 200);
        Alpha = BUILDER.defineInRange(Name + ".Alpha", 100, 0, 200);
        PosX = BUILDER.defineInRange(Name + ".PosX", 0, -100, +100);
        PosY = BUILDER.defineInRange(Name + ".PosY", 0, -100, +100);
    }

    public boolean apply(GuiGraphics Target) {
        CURRENT_SCALE = Scale.get();
        CURRENT_ALPHA = Alpha.get();
        Target.pose().pushMatrix();
        Target.pose().scale(0.01f * Scale.get(), 0.01f * Scale.get());
        Target.pose().translate(0.01f * PosX.get() * Target.guiWidth(), 0.01f * PosY.get() * Target.guiHeight());
        return Show.get() && Scale.get() > 0;
    }

    public static void reset(GuiGraphics Target) {
        CURRENT_SCALE = 100;
        CURRENT_ALPHA = 100;
        Target.pose().popMatrix();
    }
}