package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.github.i5wear.hudmanager.ModOptions;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphicsExtractor.class)
public abstract class GuiGraphicsExtractorMixin {

    @ModifyVariable(method = "setTooltipForNextFrameInternal", ordinal = 0, argsOnly = true, at = @At("HEAD"))
    private int relocateTooltip1(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", ordinal = 1, argsOnly = true, at = @At("HEAD"))
    private int relocateTooltip2(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @WrapOperation(method = "extractDeferredElements", at = @At(value = "INVOKE", target = "Ljava/lang/Runnable;run()V"))
    private void modifyTooltip(Runnable instance, Operation<Void> original) {
        HudManager.CURRENT = ModOptions.INSTANCE.Tooltip;
        if (HudManager.CURRENT.Display) original.call(instance);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}