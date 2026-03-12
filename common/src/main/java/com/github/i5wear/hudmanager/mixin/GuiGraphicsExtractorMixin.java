package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.github.i5wear.hudmanager.ModOptions;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphicsExtractor.class)
public abstract class GuiGraphicsExtractorMixin {

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int storeTooltipAxisX(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int storeTooltipAxisY(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @WrapMethod(method = "extractDeferredElements")
    private void modifyTooltip(int mouseX, int mouseY, float delta, Operation<Void> original) {
        HudManager.CURRENT = ModOptions.INSTANCE.Tooltip;
        if (HudManager.CURRENT.Display)
            original.call(mouseX, mouseY, delta);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}