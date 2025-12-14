package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.ModOptions;
import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

    @WrapMethod(method = "render")
    private void modifyDebugScreen(GuiGraphics graphics, Operation<Void> original) {
        if (ModOptions.INSTANCE.DebugScreen.apply(graphics.pose()))
            original.call(graphics);
        HudManager.reset(graphics.pose());
    }
}