package com.i5wear.hudmanager;

import net.minecraft.client.gui.GuiGraphics;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableFloat;

public class HudManager {

    public static float CURRENT_RESIZER = 1;
    public static float CURRENT_OPACITY = 1;

    public final MutableBoolean Visible = new MutableBoolean(true);
    public final MutableBoolean Preview = new MutableBoolean(true);
    public final MutableFloat Resizer = new MutableFloat(1);
    public final MutableFloat Opacity = new MutableFloat(1);
    public final MutableFloat OffsetX = new MutableFloat(0);
    public final MutableFloat OffsetY = new MutableFloat(0);

    public static int rescale(int input, float resizer) { return resizer == 0 ? Integer.MAX_VALUE : (int)(input / resizer); }

    public static int recolor(int input, float opacity) { return Math.min((int)(opacity * (input >>> 24)), 255) << 24 | input & 0xFFFFFF; }

    public boolean apply(GuiGraphics target) {
        CURRENT_RESIZER = Resizer.getValue();
        CURRENT_OPACITY = Opacity.getValue();
        target.pose().pushMatrix();
        target.pose().scale(Resizer.getValue(), Resizer.getValue());
        target.pose().translate(OffsetX.getValue() * target.guiWidth(), OffsetY.getValue() * target.guiHeight());
        return Visible.getValue();
    }

    public static void reset(GuiGraphics target) {
        CURRENT_RESIZER = 1;
        CURRENT_OPACITY = 1;
        target.pose().popMatrix();
    }
}