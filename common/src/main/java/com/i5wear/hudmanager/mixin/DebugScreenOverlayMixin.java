package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

    @WrapMethod(method = "render*")
    private void modifyDebugScreen(GuiGraphics Instance, Operation<Void> Original) {
        if (Manager.DEBUG_SCREEN.apply(Instance))
            Original.call(Instance);
        Manager.reset(Instance);
    }
}