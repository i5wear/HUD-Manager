package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Global;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Window.class)
public abstract class WindowMixin {

    /*
        A GUI Layer needs to know the width and height of screen to fit themselves in.
        While GUI Scale gets modified, screen width and height does not get modified automatically.
        This Mixin is to fix the problem, by the use of Global.CURRENT_SIZE as controller.

        Generally, a GUI Layer locates itself by GuiGraphics#guiWidth and GuiGraphics#guiHeight,
        which are just a wrapper of Window#getGuiScaleWidth and Window#getGuiScaleHeight.
        The only exception is Contextual Bar, making mixin Window.class the ultimate solution.
    */

    @ModifyReturnValue(method = "getGuiScaledWidth", at = @At("RETURN"))
    private int modifyGuiWidth(int original) { return 100 * original / Global.CURRENT_SIZE; }

    @ModifyReturnValue(method = "getGuiScaledHeight", at = @At("RETURN"))
    private int modifyGuiHeight(int original) { return 100 * original / Global.CURRENT_SIZE; }

}