package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Config;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Window.class)
public abstract class WindowMixin {

    /*
        HUD elements need to know the width and height of screen to fit themselves in.
        While GUI Scale gets modified, screen width and height does not sync automatically.
        This mixin is to fix the problem, by the use of Config.CURRENT_SIZE as controller.

        Generally, they locate themselves by GuiGraphics#guiWidth and GuiGraphics#guiHeight,
        which are just wrappers of Window#getGuiScaleWidth and Window#getGuiScaleHeight.
        The only exception is Contextual Bar, making mixin Window.class the ultimate solution.
    */

    @ModifyReturnValue(method = "getGuiScaledWidth", at = @At("TAIL"))
    private int syncScreenWidth(int original) { return 100 * original / Config.CURRENT_SIZE; }

    @ModifyReturnValue(method = "getGuiScaledHeight", at = @At("TAIL"))
    private int syncScreenHeight(int original) { return 100 * original / Config.CURRENT_SIZE; }

}