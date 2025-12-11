package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Window.class)
public abstract class WindowMixin {

    @ModifyReturnValue(method = "getGuiScaledWidth", at = @At("TAIL"))
    private int storeElementAxisX(int original) { return HudManager.rescale(original, HudManager.CURRENT_RESIZER.getValue()); }

    @ModifyReturnValue(method = "getGuiScaledHeight", at = @At("TAIL"))
    private int storeElementAxisY(int original) { return HudManager.rescale(original, HudManager.CURRENT_RESIZER.getValue()); }

}