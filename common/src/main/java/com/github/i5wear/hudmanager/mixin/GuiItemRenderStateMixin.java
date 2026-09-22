package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.config.HudManager;
import com.github.i5wear.hudmanager.render.RenderModifier;
import net.minecraft.client.renderer.state.gui.BlitRenderState;
import net.minecraft.client.renderer.state.gui.GuiItemRenderState;
import net.minecraft.util.ARGB;
import org.joml.Matrix3x2f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.IntUnaryOperator;

@Mixin(GuiItemRenderState.class)
public abstract class GuiItemRenderStateMixin implements RenderModifier {

    @Unique private final float opacity = HudManager.CURRENT.Opacity;

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 0, argsOnly = true)
    private Matrix3x2f modifyItemStateScale(Matrix3x2f original) { return HudManager.CURRENT.apply(original); }

    @Unique @Override public BlitRenderState modify(BlitRenderState original) {
        return new BlitRenderState(
            original.pipeline(), original.textureSetup(), (Matrix3x2f) original.pose(),
            original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
            ARGB.srgbLerp(opacity, 0, original.color()), original.scissorArea()
        );
    }
}
