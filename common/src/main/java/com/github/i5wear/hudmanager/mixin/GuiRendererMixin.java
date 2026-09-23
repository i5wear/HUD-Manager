package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.render.Transformer;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.renderer.state.gui.BlitRenderState;
import net.minecraft.client.renderer.state.gui.GuiItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GuiRenderer.class)
public abstract class GuiRendererMixin {

    @ModifyArg(method = "submitBlitFromItemAtlas", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/state/gui/GuiRenderState;addBlitToCurrentLayer(Lnet/minecraft/client/renderer/state/gui/BlitRenderState;)V"), index = 0)
    private BlitRenderState modifyItemState(BlitRenderState original, @Local(argsOnly = true) GuiItemRenderState instance) { return ((Transformer<BlitRenderState>)(Object)(instance)).apply(original); }

}