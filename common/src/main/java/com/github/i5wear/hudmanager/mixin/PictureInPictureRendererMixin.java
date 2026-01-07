package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.gui.render.state.BlitRenderState;
import net.minecraft.client.gui.render.state.pip.OversizedItemRenderState;
import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.IntUnaryOperator;

@Mixin(PictureInPictureRenderer.class)
public abstract class PictureInPictureRendererMixin {

    @ModifyArg(method = "blitTexture", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/render/state/GuiRenderState;submitBlitToCurrentLayer(Lnet/minecraft/client/gui/render/state/BlitRenderState;)V"))
    private BlitRenderState modifyExtraElement(BlitRenderState original, @Local(ordinal = 0, argsOnly = true) PictureInPictureRenderState instance) {
        return switch (instance) {
            case OversizedItemRenderState item -> new BlitRenderState(
                original.pipeline(), original.textureSetup(), original.pose(),
                original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
                IntUnaryOperator.class.cast(item.guiItemRenderState()).applyAsInt(original.color()), original.scissorArea()
            );
            default -> new BlitRenderState(
                original.pipeline(), original.textureSetup(), HudManager.CATEGORY.get(instance).apply(original.pose()),
                original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
                ARGB.srgbLerp(HudManager.CATEGORY.get(instance).Opacity, 0, original.color()), original.scissorArea()
            );
        };
    }
}