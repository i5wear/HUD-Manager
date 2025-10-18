package com.i5wear.hudmanager;

import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Manager {

    public final ModConfigSpec.ConfigValue<Boolean> State;
    public final ModConfigSpec.ConfigValue<Integer> Scale;
    public final ModConfigSpec.ConfigValue<Integer> OffsetX;
    public final ModConfigSpec.ConfigValue<Integer> OffsetY;

    public static final String IDENTITY = "hudmanager";
    public static volatile int CURRENT_SCALE = 100;

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

    public static int modify(int Value, int Scale) { return Scale == 0 ? Value < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE : 100 * Value / Scale; }

    public boolean apply(GuiGraphics Target) {
        CURRENT_SCALE = Scale.get();
        Target.pose().pushPose();
        Target.pose().scale(0.01f * Scale.get(), 0.01f * Scale.get(), 1);
        Target.pose().translate(0.01f * OffsetX.get() * Target.guiWidth(), 0.01f * OffsetY.get() * Target.guiHeight(), 0);
        return State.get();
    }

    public static void reset(GuiGraphics Target) {
        CURRENT_SCALE = 100;
        Target.pose().popPose();
    }

    private Manager(String Identity) {
        State = BUILDER.define(Identity + ".State", true);
        Scale = BUILDER.defineInRange(Identity + ".Scale", 100, 0, 200);
        OffsetX = BUILDER.defineInRange(Identity + ".OffsetX", 0, -100, +100);
        OffsetY = BUILDER.defineInRange(Identity + ".OffsetY", 0, -100, +100);
    }
}