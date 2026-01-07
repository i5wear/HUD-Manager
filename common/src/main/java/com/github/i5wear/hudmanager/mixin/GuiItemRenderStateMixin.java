package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiItemRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.IntUnaryOperator;

@Mixin(GuiItemRenderState.class)
public abstract class GuiItemRenderStateMixin implements IntUnaryOperator {

    @Unique private final float STORED_OPACITY = HudManager.CURRENT.Opacity;

    @Override public int applyAsInt(int original) { return ARGB.srgbLerp(STORED_OPACITY, 0, original); } // Patch #27

}