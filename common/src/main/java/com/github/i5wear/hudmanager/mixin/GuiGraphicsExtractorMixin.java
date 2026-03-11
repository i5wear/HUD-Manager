package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.github.i5wear.hudmanager.ModOptions;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphicsExtractor.class)
public abstract class GuiGraphicsExtractorMixin {

    @ModifyReturnValue(method = "guiWidth", at = @At("TAIL"))
    private int storeElementAxisX(int original) { return Math.round(original / HudManager.CURRENT.Resizer); }

    @ModifyReturnValue(method = "guiHeight", at = @At("TAIL"))
    private int storeElementAxisY(int original) { return Math.round(original / HudManager.CURRENT.Resizer); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int storeTooltipAxisX(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int storeTooltipAxisY(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @WrapOperation(method = "setTooltipForNextFrameInternal", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;deferredTooltip:Ljava/lang/Runnable;", opcode = Opcodes.PUTFIELD))
    private void modifyTooltip(GuiGraphicsExtractor graphics, Runnable input, Operation<Void> original) {
        original.call(
            graphics, (Runnable) () -> {
                if (ModOptions.INSTANCE.Tooltip.apply(graphics.pose()))
                    input.run();
                HudManager.reset(graphics.pose());
            }
        );
    }
}