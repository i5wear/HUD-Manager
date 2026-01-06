package com.github.i5wear.hudmanager;

import org.joml.Matrix3x2fStack;

import java.util.Map;
import java.util.WeakHashMap;

public class HudManager {

    public static Map<Object, Float> STORED_RESIZER = new WeakHashMap<>();
    public static Map<Object, Float> STORED_OPACITY = new WeakHashMap<>();

    public static float CURRENT_RESIZER = 1f;
    public static float CURRENT_OPACITY = 1f;

    public boolean Display = true;
    public float Resizer = 1f;
    public float Opacity = 1f;
    public float OffsetX = 0f;
    public float OffsetY = 0f;

    public boolean apply(Matrix3x2fStack target) {
        CURRENT_RESIZER = Resizer;
        CURRENT_OPACITY = Opacity;
        target.pushMatrix();
        target.translate(OffsetX, OffsetY);
        target.scale(Resizer);
        return Display;
    }

    public static void reset(Matrix3x2fStack target) {
        CURRENT_RESIZER = 1f;
        CURRENT_OPACITY = 1f;
        target.popMatrix();
    }
}