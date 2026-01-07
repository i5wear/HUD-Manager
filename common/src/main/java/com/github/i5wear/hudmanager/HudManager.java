package com.github.i5wear.hudmanager;

import org.joml.Matrix3x2fStack;

import java.util.Map;
import java.util.WeakHashMap;

public class HudManager {

    public static float CURRENT_RESIZER = 1;
    public static float CURRENT_OPACITY = 1;

    public static Map<Object, Float> STORED_RESIZER = new WeakHashMap<>();
    public static Map<Object, Float> STORED_OPACITY = new WeakHashMap<>();

    public boolean Display = true;
    public float Resizer = 1;
    public float Opacity = 1;
    public float OffsetX = 0;
    public float OffsetY = 0;

    public boolean apply(Matrix3x2fStack target) {
        CURRENT_RESIZER = Resizer;
        CURRENT_OPACITY = Opacity;
        target.pushMatrix();
        target.translate(OffsetX, OffsetY);
        target.scale(Resizer);
        return Display;
    }

    public static void reset(Matrix3x2fStack target) {
        CURRENT_RESIZER = 1;
        CURRENT_OPACITY = 1;
        target.popMatrix();
    }
}