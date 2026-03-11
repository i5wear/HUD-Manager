package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.github.i5wear.hudmanager.ModOptions;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.toasts.ToastManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ToastManager.class)
public abstract class ToastManagerMixin {

    @WrapMethod(method = "extractRenderState")
    private void modifyToastMessage(GuiGraphicsExtractor graphics, Operation<Void> original) {
        HudManager.CURRENT = ModOptions.INSTANCE.ToastMessage;
        if (HudManager.CURRENT.Display)
            original.call(graphics);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}