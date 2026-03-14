package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ToastComponent.class)
public abstract class ToastComponentMixin {

    @WrapMethod(method = "render")
    private void modifyToastMessage(GuiGraphics graphics, Operation<Void> original) {
        if (Manager.TOAST_MESSAGE.apply(graphics))
            original.call(graphics);
        Manager.reset(graphics);
    }
}