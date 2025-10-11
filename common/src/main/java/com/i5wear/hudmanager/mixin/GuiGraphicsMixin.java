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
    private int syncTooltipPosX(int Original) { return 100 * Original / Manager.TOOLTIP.Size.get(); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int syncTooltipPosY(int Original) { return 100 * Original / Manager.TOOLTIP.Size.get(); }

    @WrapOperation(method = "setTooltipForNextFrameInternal", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiGraphics;deferredTooltip:Ljava/lang/Runnable;", opcode = Opcodes.PUTFIELD))
    private void modifyTooltip(GuiGraphics Instance, Runnable Tooltip, Operation<Void> Original) {
        Original.call(
            Instance, (Runnable) () -> {
                if (Manager.TOOLTIP.apply(Instance))
                    Tooltip.run();
                Instance.pose().popMatrix();
                Manager.CURRENT_SIZE = 100;
            }
        );
    }

    @WrapMethod(method = "submitProfilerChartRenderState")
    private void modifyProfilerChart(List<ResultField> list, int PosX1, int PosY1, int PosX2, int PosY2, Operation<Void> Original) {
        GuiGraphics Instance = (GuiGraphics)(Object) this;
        PosX1 = (PosX1 + Instance.guiWidth() * Manager.DEBUG_SCREEN.PosX.get() / 100) * Manager.DEBUG_SCREEN.Size.get() / 100;
        PosX2 = (PosX2 + Instance.guiWidth() * Manager.DEBUG_SCREEN.PosX.get() / 100) * Manager.DEBUG_SCREEN.Size.get() / 100;
        PosY1 = (PosY1 + Instance.guiHeight() * Manager.DEBUG_SCREEN.PosY.get() / 100) * Manager.DEBUG_SCREEN.Size.get() / 100;
        PosY2 = (PosY2 + Instance.guiHeight() * Manager.DEBUG_SCREEN.PosY.get() / 100) * Manager.DEBUG_SCREEN.Size.get() / 100;
        Original.call(list, PosX1, PosY1, PosX2, PosY2);
    }
}