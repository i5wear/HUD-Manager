package com.github.i5wear.hudmanager;

import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;

import java.util.Map;
import java.util.WeakHashMap;

public class HudManager {

    public static HudManager DEFAULT = new HudManager();
    public static HudManager CURRENT = DEFAULT;
    public static Map<Object, HudManager> LOOKUP = new WeakHashMap<>();

    public boolean Display = true;
    public float Resizer = 1;
    public float Opacity = 1;
    public float OffsetX = 0;
    public float OffsetY = 0;

    public Matrix3x2f apply(Matrix3x2f target) {
        CURRENT = this;
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