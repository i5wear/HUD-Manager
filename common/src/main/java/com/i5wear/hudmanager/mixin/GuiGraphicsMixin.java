package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.ARGB;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @ModifyVariable(method = "submitColoredRectangle", at = @At("HEAD"), ordinal = 4, argsOnly = true)
    private int syncBackgroundAlpha1(int original) { return ARGB.color(Math.min(ARGB.alpha(original) * Manager.CURRENT_ALPHA / 100, 255), original); }

    @ModifyVariable(method = "submitColoredRectangle", at = @At("HEAD"), ordinal = 5, argsOnly = true)
    private int syncBackgroundAlpha2(int original) { return ARGB.color(Math.min(ARGB.alpha(original) * Manager.CURRENT_ALPHA / 100, 255), original); }

    @ModifyVariable(method = "submitBlit", at = @At("HEAD"), ordinal = 4, argsOnly = true)
    private int syncTextureAlpha(int original) { return ARGB.color(Math.min(ARGB.alpha(original) * Manager.CURRENT_ALPHA / 100, 255), original); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int syncTooltipPosX(int original) { return 100 * original / Manager.TOOLTIP.Scale.get(); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int syncTooltipPosY(int original) { return 100 * original / Manager.TOOLTIP.Scale.get(); }

    @WrapOperation(method = "setTooltipForNextFrameInternal", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiGraphics;deferredTooltip:Ljava/lang/Runnable;", opcode = Opcodes.PUTFIELD))
    private void modifyTooltip(GuiGraphics graphics, Runnable value, Operation<Void> original) {
        original.call(
            graphics, (Runnable) () -> {
                if (Manager.TOOLTIP.apply(graphics))
                    value.run();
                Manager.reset(graphics);
            }
        );
    }

    @WrapMethod(method = "submitProfilerChartRenderState")
    private void modifyProfilerChart(List<?> list, int x1, int y1, int x2, int y2, Operation<Void> original) {
        GuiGraphics graphics = (GuiGraphics)(Object) this;
        x1 = (x1 + graphics.guiWidth() * Manager.DEBUG_SCREEN.PosX.get() / 100) * Manager.DEBUG_SCREEN.Scale.get() / 100;
        x2 = (x2 + graphics.guiWidth() * Manager.DEBUG_SCREEN.PosX.get() / 100) * Manager.DEBUG_SCREEN.Scale.get() / 100;
        y1 = (y1 + graphics.guiHeight() * Manager.DEBUG_SCREEN.PosY.get() / 100) * Manager.DEBUG_SCREEN.Scale.get() / 100;
        y2 = (y2 + graphics.guiHeight() * Manager.DEBUG_SCREEN.PosY.get() / 100) * Manager.DEBUG_SCREEN.Scale.get() / 100;
        original.call(list, x1, y1, x2, y2);
    }
}