package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Config;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int syncTooltipPosX(int original) { return 100 * original / Config.TOOLTIP.Size.get(); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int syncTooltipPosY(int original) { return 100 * original / Config.TOOLTIP.Size.get(); }

    @WrapMethod(method = "renderDeferredElements")
    private void modifyTooltip(Operation<Void> original) {
        Config Value = Config.TOOLTIP;
        GuiGraphics arg0 = (GuiGraphics)(Object) this;
        if (Value.Show.get() && Value.Size.get() > 0) {
            Config.CURRENT_SIZE = Value.Size.get();
            arg0.pose().pushMatrix();
            arg0.pose().scale(0.01f * Value.Size.get());
            arg0.pose().translate(0.01f * Value.PosX.get() * arg0.guiWidth(), 0.01f * Value.PosY.get() * arg0.guiHeight());
            original.call();
            arg0.pose().popMatrix();
            Config.CURRENT_SIZE = 100;
        }
    }
}