package com.i5wear.hudmanager;

import net.minecraft.client.gui.GuiGraphics;

public class HudManager {

    public static volatile int CURRENT_RESIZER = 100;
    public static volatile int CURRENT_OPACITY = 100;

    public boolean Visible = true;
    public int Resizer = 100;
    public int Opacity = 100;
    public int OffsetX = 0;
    public int OffsetY = 0;

    public static int rescale(int Input, int Resizer) { return Resizer == 0 ? Input < 0 ? -2147483648 : 2147483647 : 100 * Input / Resizer; }

    public static int recolor(int Input, int Opacity) { return Math.min(Opacity * (Input >>> 24) / 100, 255) << 24 | Input & 0xFFFFFF; }

    public boolean apply(GuiGraphics Target) {
        CURRENT_RESIZER = Resizer;
        CURRENT_OPACITY = Opacity;
        Target.pose().pushMatrix();
        Target.pose().scale(0.01f * Resizer, 0.01f * Resizer);
        Target.pose().translate(0.01f * OffsetX * Target.guiWidth(), 0.01f * OffsetY * Target.guiHeight());
        return Visible;
    }

    public static void reset(GuiGraphics Target) {
        CURRENT_RESIZER = 100;
        CURRENT_OPACITY = 100;
        Target.pose().popMatrix();
    }
}