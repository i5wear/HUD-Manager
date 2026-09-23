package com.github.i5wear.hudmanager.render;

import net.minecraft.client.renderer.state.gui.BlitRenderState;
import net.minecraft.client.renderer.state.gui.GuiElementRenderState;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public interface Transformer<T> extends UnaryOperator<T> {

    Map<Class<?>, Transformer<?>> REGISTRY = new IdentityHashMap<>();

    static <T extends GuiElementRenderState> void addElementRenderer(Class<T> key, Transformer<T> value) { REGISTRY.put(key, value); }

    static <T extends PictureInPictureRenderState> void addCustomRenderer(Class<T> key, Transformer<BlitRenderState> value) { REGISTRY.put(key, value); }

    static GuiElementRenderState render(GuiElementRenderState input) {
        for (Class<?> clazz = input.getClass(); clazz != null; clazz = clazz.getSuperclass())
            if (REGISTRY.containsKey(clazz))
                return ((UnaryOperator<GuiElementRenderState>) REGISTRY.get(clazz)).apply(input);
        return input;
    }
}
