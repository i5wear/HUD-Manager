package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HUDManager;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Window.class)
public abstract class WindowMixin {

    @ModifyReturnValue(method = "getGuiScaledWidth", at = @At("RETURN"))
    private int scaleWidth(int original) { return (int) (original / HUDManager.SCALE); }

    @ModifyReturnValue(method = "getGuiScaledHeight", at = @At("RETURN"))
    private int scaleHeight(int original) { return (int) (original / HUDManager.SCALE); }

}