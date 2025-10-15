package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Window.class)
public abstract class WindowMixin {

    @ModifyReturnValue(method = "getGuiScaledWidth", at = @At("TAIL"))
    private int syncElementPosX(int original) { return Manager.modifyPoint(original, Manager.CURRENT_SCALE); }

    @ModifyReturnValue(method = "getGuiScaledHeight", at = @At("TAIL"))
    private int syncElementPosY(int original) { return Manager.modifyPoint(original, Manager.CURRENT_SCALE); }

}