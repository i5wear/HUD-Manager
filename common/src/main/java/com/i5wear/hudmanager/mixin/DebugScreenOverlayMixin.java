package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HudOptions;
import com.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

    @WrapMethod(method = "render")
    private void modifyDebugScreen(GuiGraphics graphics, Operation<Void> original) {
        if (HudOptions.INSTANCE.DebugScreen.apply(graphics))
            original.call(graphics);
        HudManager.reset(graphics);
    }
}