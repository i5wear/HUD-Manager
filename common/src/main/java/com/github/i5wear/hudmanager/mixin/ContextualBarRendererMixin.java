package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.contextualbar.ContextualBarRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ContextualBarRenderer.class)
public interface ContextualBarRendererMixin {

    @ModifyExpressionValue(method = "left", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledWidth()I"))
    private int storeBarElementAxisX(int original) { return Math.round(original / HudManager.CURRENT.Resizer); }

    @ModifyExpressionValue(method = "top", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    private int storeBarElementAxisY(int original) { return Math.round(original / HudManager.CURRENT.Resizer); }

}