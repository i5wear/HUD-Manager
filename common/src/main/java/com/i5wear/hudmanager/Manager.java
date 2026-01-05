package com.i5wear.hudmanager;

import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Manager {

    public final ModConfigSpec.ConfigValue<Boolean> Display;
    public final ModConfigSpec.ConfigValue<Integer> Resizer;
    public final ModConfigSpec.ConfigValue<Integer> Opacity;
    public final ModConfigSpec.ConfigValue<Integer> OffsetX;
    public final ModConfigSpec.ConfigValue<Integer> OffsetY;

    public static final String IDENTITY = "hudmanager";
    public static volatile int CURRENT_RESIZER = 100;
    public static volatile int CURRENT_OPACITY = 100;

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final Manager ACTION_BAR = new Manager("Action_Bar");
    public static final Manager BOSS_BAR = new Manager("Boss_Bar");
    public static final Manager CLOSED_CAPTION = new Manager("Closed_Caption");
    public static final Manager CROSSHAIR = new Manager("Crosshair");
    public static final Manager DEBUG_SCREEN = new Manager("Debug_Screen");
    public static final Manager HOTBAR_GROUP = new Manager("Hotbar_Group");
    public static final Manager PLAYER_LIST = new Manager("Player_List");
    public static final Manager SCOREBOARD = new Manager("Scoreboard");
    public static final Manager SCREEN_TITLE = new Manager("Screen_Title");
    public static final Manager STATUS_EFFECT = new Manager("Status_Effect");
    public static final Manager TOAST_MESSAGE = new Manager("Toast_Message");
    public static final Manager TOOLTIP = new Manager("Tooltip");
    public static final ModConfigSpec CONFIG = BUILDER.build();

    public static int modifyVector(int Vector, int Scale) { return Scale == 0 ? Vector < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE : 100 * Vector / Scale; }

    public static int modifyColor(int Color, int Opacity) { return Math.min(Opacity * (Color >>> 24) / 100, 255) << 24 | Color & 0xFFFFFF; }

    public boolean apply(GuiGraphics Target) {
        CURRENT_RESIZER = Resizer.get();
        CURRENT_OPACITY = Opacity.get();
        Target.pose().pushMatrix();
        Target.pose().scale(0.01f * Resizer.get());
        Target.pose().translate(0.01f * OffsetX.get() * Target.guiWidth(), 0.01f * OffsetY.get() * Target.guiHeight());
        return Display.get();
    }

    public static void reset(GuiGraphics Target) {
        CURRENT_RESIZER = 100;
        CURRENT_OPACITY = 100;
        Target.pose().popMatrix();
    }

    private Manager(String Identity) {
        Display = BUILDER.define(Identity + ".Display", true);
        Resizer = BUILDER.defineInRange(Identity + ".Resizer", 100, 0, 200);
        Opacity = BUILDER.defineInRange(Identity + ".Opacity", 100, 0, 200);
        OffsetX = BUILDER.defineInRange(Identity + ".OffsetX", 0, -100, +100);
        OffsetY = BUILDER.defineInRange(Identity + ".OffsetY", 0, -100, +100);
    }
}