package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.gui.render.state.BlitRenderState;
import net.minecraft.client.gui.render.state.pip.GuiProfilerChartRenderState;
import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import net.minecraft.util.ARGB;
import org.joml.Matrix3x2f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PictureInPictureRenderer.class)
public abstract class PictureInPictureRendererMixin {

    @ModifyArg(method = "blitTexture", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/render/state/GuiRenderState;submitBlitToCurrentLayer(Lnet/minecraft/client/gui/render/state/BlitRenderState;)V"))
    private BlitRenderState modifyProfilerChart(BlitRenderState original, @Local(ordinal = 0, argsOnly = true) PictureInPictureRenderState instance) {
        return !(instance instanceof GuiProfilerChartRenderState) ? original : new BlitRenderState(
            original.pipeline(), original.textureSetup(), new Matrix3x2f().scale(0.01f * Manager.DEBUG_SCREEN.Scale.get()),
            original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
            ARGB.color(Math.min(255 * Manager.DEBUG_SCREEN.Alpha.get() / 100, 255), -1), original.scissorArea()
        );
    }
}