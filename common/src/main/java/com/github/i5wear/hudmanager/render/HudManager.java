package com.github.i5wear.hudmanager.render;

import net.minecraft.client.renderer.state.gui.BlitRenderState;
import net.minecraft.client.renderer.state.gui.ColoredRectangleRenderState;
import net.minecraft.client.renderer.state.gui.TiledBlitRenderState;
import net.minecraft.util.ARGB;
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

    public boolean Display = true;
    public float Resizer = 1;
    public float Opacity = 1;
    public float OffsetX = 0;
    public float OffsetY = 0;

    public Matrix3x2f apply(Matrix3x2fc input) {
        var output = new Matrix3x2f();
        output.translate(OffsetX, OffsetY);
        output.scale(Resizer, Resizer);
        return output.mul(input);
    }

    static {
        Transformer.addElementRenderer(
            ColoredRectangleRenderState.class, input -> new ColoredRectangleRenderState(
                input.pipeline(), input.textureSetup(), HudManager.CURRENT.apply(input.pose()),
                input.x0(), input.y0(), input.x1(), input.y1(), ARGB.multiplyAlpha(input.col1(), HudManager.CURRENT.Opacity),
                ARGB.multiplyAlpha(input.col2(), HudManager.CURRENT.Opacity), input.scissorArea()
            )
        );
        Transformer.addElementRenderer(
            BlitRenderState.class, input -> new BlitRenderState(
                input.pipeline(), input.textureSetup(), HudManager.CURRENT.apply(input.pose()),
                input.x0(), input.y0(), input.x1(), input.y1(), input.u0(), input.u1(), input.v0(), input.v1(),
                ARGB.multiplyAlpha(input.color(), HudManager.CURRENT.Opacity), input.scissorArea()
            )
        );
        Transformer.addElementRenderer(
            TiledBlitRenderState.class, input -> new TiledBlitRenderState(
                input.pipeline(), input.textureSetup(), HudManager.CURRENT.apply(input.pose()), input.tileWidth(), input.tileHeight(),
                input.x0(), input.y0(), input.x1(), input.y1(), input.u0(), input.u1(), input.v0(), input.v1(),
                ARGB.multiplyAlpha(input.color(), HudManager.CURRENT.Opacity), input.scissorArea()
            )
        );
    }
}