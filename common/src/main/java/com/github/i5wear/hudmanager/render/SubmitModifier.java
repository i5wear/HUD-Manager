package com.github.i5wear.hudmanager.render;

import net.minecraft.client.renderer.state.gui.GuiElementRenderState;

public interface SubmitModifier<T extends GuiElementRenderState> {

    SubmitModifier<GuiElementRenderState> DEFAULT = input -> input;

    T modify(T input);

}
