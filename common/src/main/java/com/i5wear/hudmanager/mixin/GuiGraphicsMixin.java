package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @ModifyReturnValue(method = "guiWidth", at = @At("RETURN"))
    private int modifyGuiWidth(int original) { return 100 * original / HudManager.Temp.SCALE; }

    @ModifyReturnValue(method = "guiHeight", at = @At("RETURN"))
    private int modifyGuiHeight(int original) { return 100 * original / HudManager.Temp.SCALE; }

}