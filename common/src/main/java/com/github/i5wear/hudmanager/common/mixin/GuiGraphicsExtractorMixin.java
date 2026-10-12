package com.github.i5wear.hudmanager.common.mixin;

import com.github.i5wear.hudmanager.common.config.ModOptions;
import com.github.i5wear.hudmanager.common.render.HudManager;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphicsExtractor.class)
public abstract class GuiGraphicsExtractorMixin {

    @ModifyVariable(method = "lambda$setTooltipForNextFrameInternal$0", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int modifyTooltipAxisX(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @ModifyVariable(method = "lambda$setTooltipForNextFrameInternal$0", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int modifyTooltipAxisY(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @WrapOperation(method = "extractDeferredElements", at = @At(value = "INVOKE", target = "Ljava/lang/Runnable;run()V"))
    private void wrapTooltip(Runnable instance, Operation<Void> original) {
        HudManager.CURRENT = ModOptions.INSTANCE.Tooltip;
        if (HudManager.CURRENT.Display)
            original.call(instance);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}