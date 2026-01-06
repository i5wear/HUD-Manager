package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.ModOptions;
import com.github.i5wear.hudmanager.HudManager;
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
    private int storeTextureColor1(int original) { return ARGB.multiplyAlpha(original, HudManager.CURRENT_OPACITY); }

    @ModifyVariable(method = "submitTiledBlit", at = @At("HEAD"), ordinal = 6, argsOnly = true)
    private int storeTextureColor2(int original) { return ARGB.multiplyAlpha(original, HudManager.CURRENT_OPACITY); }

    @ModifyVariable(method = "submitColoredRectangle", at = @At("HEAD"), ordinal = 4, argsOnly = true)
    private int storeBackgroundColor1(int original) { return ARGB.multiplyAlpha(original, HudManager.CURRENT_OPACITY); }

    @ModifyVariable(method = "fillGradient", at = @At("HEAD"), ordinal = 5, argsOnly = true)
    private int storeBackgroundColor2(int original) { return ARGB.multiplyAlpha(original, HudManager.CURRENT_OPACITY); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int storeTooltipAxisX(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @ModifyVariable(method = "setTooltipForNextFrameInternal", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int storeTooltipAxisY(int original) { return Math.round(original / ModOptions.INSTANCE.Tooltip.Resizer); }

    @WrapOperation(method = "setTooltipForNextFrameInternal", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/GuiGraphics;deferredTooltip:Ljava/lang/Runnable;", opcode = Opcodes.PUTFIELD))
    private void modifyTooltip(GuiGraphics graphics, Runnable instance, Operation<Void> original) {
        original.call(
            graphics, (Runnable) () -> {
                if (ModOptions.INSTANCE.Tooltip.apply(graphics.pose()))
                    instance.run();
                HudManager.reset(graphics.pose());
            }
        );
    }
}