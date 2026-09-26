package com.github.i5wear.hudmanager.common.render;

import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;

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

    public boolean Display = true;
    public float Resizer = 1, Opacity = 1;
    public float OffsetX = 0, OffsetY = 0;

    public Matrix3x2f apply(Matrix3x2fc original) { return new Matrix3x2f().translate(OffsetX, OffsetY).scale(Resizer).mul(original); }

    public Matrix3x2f reset(Matrix3x2fc original) { return new Matrix3x2f().scale(1.0f / Resizer).translate(-OffsetX, -OffsetY).mul(original); }

}