package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ToastManager.class)
public abstract class ToastManagerMixin {

    @WrapMethod(method = "render")
    private void modifyToast(GuiGraphics instance, Operation<Void> original) {
        if (Manager.TOAST.apply(instance))
            original.call(instance);
        instance.pose().popMatrix();
        Manager.CURRENT_SIZE = 100;
    }
}