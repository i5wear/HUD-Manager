package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.state.gui.BlitRenderState;
import net.minecraft.client.renderer.state.gui.pip.OversizedItemRenderState;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PictureInPictureRenderer.class)
public abstract class PictureInPictureRendererMixin {

    @ModifyArg(method = "blitTexture", index = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/state/gui/GuiRenderState;addBlitToCurrentLayer(Lnet/minecraft/client/renderer/state/gui/BlitRenderState;)V"))
    private BlitRenderState modifyExtraState(BlitRenderState original, @Local(ordinal = 0, argsOnly = true) PictureInPictureRenderState instance) {
        return switch (instance) {
            case PictureInPictureRenderState output when HudManager.CONTENT.containsKey(output) -> new BlitRenderState(
                original.pipeline(), original.textureSetup(), HudManager.CONTENT.get(output).apply(original.pose()),
                original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
                ARGB.srgbLerp(HudManager.CONTENT.get(output).Opacity, 0, original.color()), original.scissorArea(), original.bounds()
            );
            case OversizedItemRenderState output when HudManager.CONTENT.containsKey(output.guiItemRenderState()) -> new BlitRenderState(
                original.pipeline(), original.textureSetup(), HudManager.CONTENT.get(output.guiItemRenderState()).apply(original.pose()),
                original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
                ARGB.srgbLerp(HudManager.CONTENT.get(output.guiItemRenderState()).Opacity, 0, original.color()), original.scissorArea(), original.bounds()
            );
            default -> original;
        };
    }
}