package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HudOptions;
import com.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.gui.render.state.BlitRenderState;
import net.minecraft.client.gui.render.state.pip.GuiProfilerChartRenderState;
import net.minecraft.client.gui.render.state.pip.OversizedItemRenderState;
import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import org.joml.Matrix3x2f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.IntSupplier;

@Mixin(PictureInPictureRenderer.class)
public abstract class PictureInPictureRendererMixin {

    @ModifyArg(method = "blitTexture", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/render/state/GuiRenderState;submitBlitToCurrentLayer(Lnet/minecraft/client/gui/render/state/BlitRenderState;)V"))
    private BlitRenderState modifySpecialElement(BlitRenderState original, @Local(ordinal = 0, argsOnly = true) PictureInPictureRenderState instance) {
        return switch (instance) {
            case GuiProfilerChartRenderState ignore -> new BlitRenderState(
                original.pipeline(), original.textureSetup(), new Matrix3x2f().scale(HudOptions.INSTANCE.DebugScreen.Resizer),
                original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
                HudManager.recolor(0xFFFFFFFF, HudOptions.INSTANCE.DebugScreen.Opacity), original.scissorArea()
            );
            case OversizedItemRenderState item -> new BlitRenderState(
                original.pipeline(), original.textureSetup(), original.pose(),
                original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
                ((IntSupplier)(Object) item.guiItemRenderState()).getAsInt(), original.scissorArea()
            );
            default -> original;
        };
    }
}