package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.profiling.ResultField;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int syncTooltipPosX(int original) { return 100 * original / Manager.TOOLTIP.Size.get(); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int syncTooltipPosY(int original) { return 100 * original / Manager.TOOLTIP.Size.get(); }

    @WrapOperation(method = "setTooltipForNextFrameInternal", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiGraphics;deferredTooltip:Ljava/lang/Runnable;", opcode = Opcodes.PUTFIELD))
    private void modifyTooltip(GuiGraphics instance, Runnable variable, Operation<Void> original) {
        Runnable OriginalTooltip = variable;
        variable = () -> {
            if (Manager.TOOLTIP.apply(instance))
                OriginalTooltip.run();
            instance.pose().popMatrix();
            Manager.CURRENT_SIZE = 100;
        };
        original.call(instance, variable);
    }

    @WrapMethod(method = "submitProfilerChartRenderState")
    private void modifyProfiler(List<ResultField> list, int PosX1, int PosY1, int PosX2, int PosY2, Operation<Void> original) {
        GuiGraphics instance = (GuiGraphics)(Object) this;
        PosX1 = (PosX1 + instance.guiWidth() * Manager.DEBUG_SCREEN.PosX.get() / 100) * Manager.DEBUG_SCREEN.Size.get() / 100;
        PosX2 = (PosX2 + instance.guiWidth() * Manager.DEBUG_SCREEN.PosX.get() / 100) * Manager.DEBUG_SCREEN.Size.get() / 100;
        PosY1 = (PosY1 + instance.guiHeight() * Manager.DEBUG_SCREEN.PosY.get() / 100) * Manager.DEBUG_SCREEN.Size.get() / 100;
        PosY2 = (PosY2 + instance.guiHeight() * Manager.DEBUG_SCREEN.PosY.get() / 100) * Manager.DEBUG_SCREEN.Size.get() / 100;
        original.call(list, PosX1, PosY1, PosX2, PosY2);
    }
}