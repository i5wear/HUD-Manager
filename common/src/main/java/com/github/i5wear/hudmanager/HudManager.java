package com.github.i5wear.hudmanager;

import org.joml.Matrix3x2d;
import org.joml.Matrix3x2dc;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;

import java.util.Map;
import java.util.WeakHashMap;

public class HudManager {

    public static HudManager DEFAULT = new HudManager();
    public static HudManager CURRENT = DEFAULT;
    public static Map<Object, HudManager> CONTENT = new WeakHashMap<>();

    public volatile boolean Display = true;
    public volatile float Resizer = 1;
    public volatile float Opacity = 1;
    public volatile float OffsetX = 0;
    public volatile float OffsetY = 0;

    public Matrix3x2f apply(Matrix3x2fc input) { return new Matrix3x2f(input).translate(OffsetX, OffsetY).scale(Resizer); }

    public Matrix3x2d apply(Matrix3x2dc input) { return new Matrix3x2d(input).translate(OffsetX, OffsetY).scale(Resizer); }

}