package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.gui.render.state.BlitRenderState;
import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import net.minecraft.util.ARGB;
import org.joml.Matrix3x2f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PictureInPictureRenderer.class)
public abstract class PictureInPictureRendererMixin {

    @ModifyArg(method = "blitTexture", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/render/state/GuiRenderState;submitBlitToCurrentLayer(Lnet/minecraft/client/gui/render/state/BlitRenderState;)V"))
    private BlitRenderState transformElement(BlitRenderState original, @Local(ordinal = 0, argsOnly = true) PictureInPictureRenderState instance) {
        return new BlitRenderState(
            original.pipeline(), original.textureSetup(), original.pose().scale(HudManager.STORED_RESIZER.getOrDefault(instance, 1f), new Matrix3x2f()),
            original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
            ARGB.srgbLerp(HudManager.STORED_OPACITY.getOrDefault(instance, 1f), 0, original.color()), original.scissorArea() // Patch #27
        );
    }
}