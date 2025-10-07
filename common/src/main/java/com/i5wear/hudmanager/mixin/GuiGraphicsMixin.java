package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphics;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int syncTooltipPosX(int original) { return 100 * original / Config.TOOLTIP.Size.get(); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int syncTooltipPosY(int original) { return 100 * original / Config.TOOLTIP.Size.get(); }

    @WrapOperation(method = "setTooltipForNextFrameInternal", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiGraphics;deferredTooltip:Ljava/lang/Runnable;", opcode = Opcodes.PUTFIELD))
    private void modifyTooltip(GuiGraphics instance, Runnable variable, Operation<Void> original) {
        Runnable OriginalTooltip = variable;
        variable = () -> {
            Config Value = Config.TOOLTIP;
            if (Value.Show.get() && Value.Size.get() > 0) {
                Config.CURRENT_SIZE = Value.Size.get();
                instance.pose().pushMatrix();
                instance.pose().scale(0.01f * Value.Size.get());
                instance.pose().translate(0.01f * Value.PosX.get() * instance.guiWidth(), 0.01f * Value.PosY.get() * instance.guiHeight());
                OriginalTooltip.run();
                instance.pose().popMatrix();
                Config.CURRENT_SIZE = 100;
            }
        };
        original.call(instance, variable);
    }
}