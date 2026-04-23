package com.github.i5wear.hudmanager;

import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * <p> The core of HUD Manager. </p>
 * <p> Declares options for HUD elements. </p>
 * <p> Performs render state transformation. </p>
 *
 * @author i5wear
 */
public class HudManager {

    public static HudManager DEFAULT = new HudManager();
    public static HudManager CURRENT = DEFAULT;
    public static Map<Object, HudManager> CONTENT = new WeakHashMap<>();

    public volatile boolean Display = true;
    public volatile float Resizer = 1;
    public volatile float Opacity = 1;
    public volatile float OffsetX = 0;
    public volatile float OffsetY = 0;

    public Matrix3x2f apply(Matrix3x2fc input) {
        var output = new Matrix3x2f();
        output.translate(OffsetX, OffsetY);
        output.scale(Resizer, Resizer);
        return output.mul(input);
    }
}