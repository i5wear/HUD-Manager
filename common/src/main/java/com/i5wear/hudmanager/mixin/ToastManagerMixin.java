package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Config;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.ToastManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ToastManager.class)
public abstract class ToastManagerMixin {

    @WrapMethod(method = "render")
    private void modifyToast(GuiGraphics instance, Operation<Void> original) {
        Config Value = Config.TOAST;
        if (Value.Show.get() && Value.Size.get() > 0) {
            Config.CURRENT_SIZE = Value.Size.get();
            instance.pose().pushPose();
            instance.pose().scale(0.01f * Value.Size.get(), 0.01f * Value.Size.get(), 1);
            instance.pose().translate(0.01f * Value.PosX.get() * instance.guiWidth(), 0.01f * Value.PosY.get() * instance.guiHeight(), 0);
            original.call(instance);
            instance.pose().popPose();
            Config.CURRENT_SIZE = 100;
        }
    }
}