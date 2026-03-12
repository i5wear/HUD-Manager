package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.platform.Window;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Window.class)
public class WindowMixin {

    @ModifyExpressionValue(method = "getGuiScaledWidth", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lcom/mojang/blaze3d/platform/Window;guiScaledWidth:I"))
    private int storeElementAxisX(int original) { return Math.round(original / HudManager.CURRENT.Resizer); }

    @ModifyExpressionValue(method = "getGuiScaledHeight", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lcom/mojang/blaze3d/platform/Window;guiScaledHeight:I"))
    private int storeElementAxisY(int original) { return Math.round(original / HudManager.CURRENT.Resizer); }

}