package com.github.i5wear.hudmanager.render;

import net.minecraft.client.renderer.state.gui.BlitRenderState;
import net.minecraft.client.renderer.state.gui.ColoredRectangleRenderState;
import net.minecraft.client.renderer.state.gui.TiledBlitRenderState;
import net.minecraft.client.renderer.state.gui.pip.OversizedItemRenderState;
import net.minecraft.util.ARGB;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class HudRenderer {

    private static final Map<Object, HudManager> CONTENT = new WeakHashMap<>();

    private static final Map<Class<?>, UnaryOperator<?>> ELEMENT_REGISTRY = new IdentityHashMap<>();

    private static final Map<Class<?>, Function<?, UnaryOperator<BlitRenderState>>> CUSTOM_REGISTRY = new IdentityHashMap<>();

    public static <T> void addElementRenderer(Class<T> clazz, UnaryOperator<T> value) { ELEMENT_REGISTRY.put(clazz, value); }

    public static <T> void addCustomRenderer(Class<T> clazz, Function<T, UnaryOperator<BlitRenderState>> value) { CUSTOM_REGISTRY.put(clazz, value); }

    public static <T> T render(T original) {
        for (Class<?> clazz = original.getClass(); clazz != null; clazz = clazz.getSuperclass())
            if (ELEMENT_REGISTRY.containsKey(clazz))
                return ((UnaryOperator<T>)(ELEMENT_REGISTRY.get(clazz))).apply(original);
        if (HudManager.CURRENT != HudManager.DEFAULT)
            CONTENT.put(original, HudManager.CURRENT);
        return original;
    }

    public static <T> BlitRenderState render(T instance, BlitRenderState original) {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass())
            if (CUSTOM_REGISTRY.containsKey(clazz))
                return ((Function<T, UnaryOperator<BlitRenderState>>)(CUSTOM_REGISTRY.get(clazz))).apply(instance).apply(original);
        return new BlitRenderState(
            original.pipeline(), original.textureSetup(), CONTENT.getOrDefault(instance, HudManager.DEFAULT).apply(original.pose()),
            original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
            ARGB.srgbLerp(CONTENT.getOrDefault(instance, HudManager.DEFAULT).Opacity, 0, original.color()), original.scissorArea(), original.bounds()
        );
    }

    static {
        HudRenderer.addElementRenderer(
            ColoredRectangleRenderState.class, original -> new ColoredRectangleRenderState(
                original.pipeline(), original.textureSetup(), HudManager.CURRENT.apply(original.pose()),
                original.x0(), original.y0(), original.x1(), original.y1(), ARGB.multiplyAlpha(original.col1(), HudManager.CURRENT.Opacity),
                ARGB.multiplyAlpha(original.col2(), HudManager.CURRENT.Opacity), original.scissorArea(), original.bounds()
            )
        );
        HudRenderer.addElementRenderer(
            BlitRenderState.class, original -> new BlitRenderState(
                original.pipeline(), original.textureSetup(), HudManager.CURRENT.apply(original.pose()),
                original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
                ARGB.multiplyAlpha(original.color(), HudManager.CURRENT.Opacity), original.scissorArea(), original.bounds()
            )
        );
        HudRenderer.addElementRenderer(
            TiledBlitRenderState.class, original -> new TiledBlitRenderState(
                original.pipeline(), original.textureSetup(), HudManager.CURRENT.apply(original.pose()), original.tileWidth(), original.tileHeight(),
                original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
                ARGB.multiplyAlpha(original.color(), HudManager.CURRENT.Opacity), original.scissorArea(), original.bounds()
            )
        );
        HudRenderer.addCustomRenderer(
            OversizedItemRenderState.class, source -> original -> new BlitRenderState(
                original.pipeline(), original.textureSetup(), CONTENT.getOrDefault(source.guiItemRenderState(), HudManager.DEFAULT).apply(original.pose()),
                original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
                ARGB.srgbLerp(CONTENT.getOrDefault(source.guiItemRenderState(), HudManager.DEFAULT).Opacity, 0, original.color()), original.scissorArea(), original.bounds()
            )
        );
    }
}
