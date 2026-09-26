package com.github.i5wear.hudmanager.common.mixin;

import com.github.i5wear.hudmanager.common.config.ModOptions;
import com.github.i5wear.hudmanager.common.render.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

    @WrapMethod(method = "extractRenderState")
    private void wrapDebugScreen(GuiGraphicsExtractor graphics, Operation<Void> original) {
        HudManager.CURRENT = ModOptions.INSTANCE.DebugScreen;
        if (HudManager.CURRENT.Display)
            original.call(graphics);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}