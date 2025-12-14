package com.github.i5wear.hudmanager.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.render.state.BlitRenderState;
import net.minecraft.client.gui.render.state.GuiItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.IntSupplier;

@Mixin(GuiRenderer.class)
public abstract class GuiRendererMixin {

    @ModifyArg(method = "submitBlitFromItemAtlas", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/render/state/GuiRenderState;submitBlitToCurrentLayer(Lnet/minecraft/client/gui/render/state/BlitRenderState;)V"))
    private BlitRenderState storeItemColor(BlitRenderState original, @Local(ordinal = 0, argsOnly = true) GuiItemRenderState instance) {
        return new BlitRenderState(
            original.pipeline(), original.textureSetup(), original.pose(),
            original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
            IntSupplier.class.cast(instance).getAsInt(), original.scissorArea()
        );
    }
}