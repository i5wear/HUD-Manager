package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.ModOptions;
import net.minecraft.client.gui.components.IMEPreeditOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(IMEPreeditOverlay.class)
public abstract class IMEPreeditOverlayMixin {

    @ModifyVariable(method = "updateInputPosition", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int modifyPreeditTooltipAxisX(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @ModifyVariable(method = "updateInputPosition", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int modifyPreeditTooltipAxisY(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

}