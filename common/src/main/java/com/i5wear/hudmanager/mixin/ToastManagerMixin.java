package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HudOptions;
import com.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ToastManager.class)
public abstract class ToastManagerMixin {

    @WrapMethod(method = "render")
    private void modifyToast(GuiGraphics graphics, Operation<Void> original) {
        if (HudOptions.INSTANCE.Toast.apply(graphics))
            original.call(graphics);
        HudManager.reset(graphics);
    }
}