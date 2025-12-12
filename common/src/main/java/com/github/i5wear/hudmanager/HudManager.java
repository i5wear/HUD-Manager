package com.github.i5wear.hudmanager;

import net.minecraft.client.gui.GuiGraphics;

public class HudManager {

    public static float CURRENT_RESIZER = 1;
    public static float CURRENT_OPACITY = 1;

    public boolean Display = true;
    public float Resizer = 1, Opacity = 1;
    public float OffsetX = 0, OffsetY = 0;

    public boolean apply(GuiGraphics target) {
        CURRENT_RESIZER = Resizer;
        CURRENT_OPACITY = Opacity;
        target.pose().pushMatrix();
        target.pose().translate(OffsetX, OffsetY);
        target.pose().scale(Resizer);
        return Display;
    }

    public static void reset(GuiGraphics target) {
        CURRENT_RESIZER = 1;
        CURRENT_OPACITY = 1;
        target.pose().popMatrix();
    }
}