package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Config;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ToastManager.class)
public class ToastManagerMixin {

    @WrapMethod(method = "render")
    private void modifyToast(GuiGraphics arg0, Operation<Void> original) {
        Config Value = Config.TOAST;
        if (Value.Show.get() && Value.Size.get() > 0) {
            Config.CURRENT_SIZE = Value.Size.get();
            arg0.pose().pushMatrix();
            arg0.pose().scale(0.01f * Value.Size.get());
            arg0.pose().translate(0.01f * Value.PosX.get() * arg0.guiWidth(), 0.01f * Value.PosY.get() * arg0.guiHeight());
            original.call(arg0);
            arg0.pose().popMatrix();
            Config.CURRENT_SIZE = 100;
        }
    }
}