package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.ARGB;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @ModifyVariable(method = "submitBlit", at = @At("HEAD"), ordinal = 4, argsOnly = true)
    private int syncTextureAlpha1(int original) { return ARGB.color(Math.min(ARGB.alpha(original) * Manager.CURRENT_ALPHA / 100, 255), original); }

    @ModifyVariable(method = "submitTiledBlit", at = @At("HEAD"), ordinal = 6, argsOnly = true)
    private int syncTextureAlpha2(int original) { return ARGB.color(Math.min(ARGB.alpha(original) * Manager.CURRENT_ALPHA / 100, 255), original); }

    @ModifyVariable(method = "submitColoredRectangle", at = @At("HEAD"), ordinal = 4, argsOnly = true)
    private int syncBackgroundAlpha1(int original) { return ARGB.color(Math.min(ARGB.alpha(original) * Manager.CURRENT_ALPHA / 100, 255), original); }

    @ModifyVariable(method = "submitColoredRectangle", at = @At("HEAD"), ordinal = 5, argsOnly = true)
    private int syncBackgroundAlpha2(int original) { return ARGB.color(Math.min(ARGB.alpha(original) * Manager.CURRENT_ALPHA / 100, 255), original); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int syncTooltipPosX(int original) { return 100 * original / Manager.TOOLTIP.Scale.get(); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int syncTooltipPosY(int original) { return 100 * original / Manager.TOOLTIP.Scale.get(); }

    @WrapOperation(method = "setTooltipForNextFrameInternal", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiGraphics;deferredTooltip:Ljava/lang/Runnable;", opcode = Opcodes.PUTFIELD))
    private void modifyTooltip(GuiGraphics graphics, Runnable instance, Operation<Void> original) {
        original.call(
            graphics, (Runnable) () -> {
                if (Manager.TOOLTIP.apply(graphics))
                    instance.run();
                Manager.reset(graphics);
            }
        );
    }
}