package com.github.i5wear.hudmanager.render;

import net.minecraft.client.renderer.state.gui.BlitRenderState;
import net.minecraft.util.ARGB;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface Transformer<T> extends UnaryOperator<T> {

    Map<Class<?>, Transformer<?>> ELEMENT_REGISTRY = new IdentityHashMap<>();

    Map<Class<?>, Function<?, Transformer<BlitRenderState>>> CUSTOM_REGISTRY = new IdentityHashMap<>();

    static <T> void addElementRenderer(Class<T> clazz, Transformer<T> value) { ELEMENT_REGISTRY.put(clazz, value); }

    static <T> void addCustomRenderer(Class<T> clazz, Function<T, Transformer<BlitRenderState>> value) { CUSTOM_REGISTRY.put(clazz, value); }

    static <T> T render(T input) {
        for (Class<?> clazz = input.getClass(); clazz != null; clazz = clazz.getSuperclass())
            if (ELEMENT_REGISTRY.containsKey(clazz))
                return ((Transformer<T>)(ELEMENT_REGISTRY.get(clazz))).apply(input);
        if (HudManager.CURRENT != HudManager.DEFAULT)
            HudManager.CONTENT.put(input, HudManager.CURRENT);
        return input;
    }

    static <T> BlitRenderState render(T source, BlitRenderState input) {
        for (Class<?> clazz = source.getClass(); clazz != null; clazz = clazz.getSuperclass())
            if (CUSTOM_REGISTRY.containsKey(clazz))
                return ((Function<T, Transformer<BlitRenderState>>)(CUSTOM_REGISTRY.get(clazz))).apply(source).apply(input);
        return new BlitRenderState(
            input.pipeline(), input.textureSetup(), HudManager.CONTENT.getOrDefault(source, HudManager.DEFAULT).apply(input.pose()),
            input.x0(), input.y0(), input.x1(), input.y1(), input.u0(), input.u1(), input.v0(), input.v1(),
            ARGB.srgbLerp(HudManager.CONTENT.getOrDefault(source, HudManager.DEFAULT).Opacity, 0, input.color()), input.scissorArea()
        );
    }
}
