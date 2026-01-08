package com.github.i5wear.hudmanager;

import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;

import java.util.Map;
import java.util.WeakHashMap;

public class HudManager {

    public static HudManager DEFAULT = new HudManager();
    public static HudManager CURRENT = DEFAULT;
    public static Map<Object, HudManager> STORAGE = new WeakHashMap<>();

    public volatile boolean Display = true;
    public volatile float Resizer = 1;
    public volatile float Opacity = 1;
    public volatile float OffsetX = 0;
    public volatile float OffsetY = 0;

    public Matrix3x2f apply(Matrix3x2f target) {
        var output = new Matrix3x2f(target);
        output.translate(OffsetX, OffsetY);
        output.scale(Resizer);
        return output;
    }

    public boolean apply(Matrix3x2fStack target) {
        CURRENT = this;
        target.pushMatrix();
        target.translate(OffsetX, OffsetY);
        target.scale(Resizer);
        return Display;
    }

    public static void reset(Matrix3x2fStack target) {
        CURRENT = DEFAULT;
        target.popMatrix();
    }
}