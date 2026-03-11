package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.github.i5wear.hudmanager.ModOptions;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

    @WrapMethod(method = "extractRenderState")
    private void modifyDebugScreen(GuiGraphicsExtractor graphics, Operation<Void> original) {
        HudManager.CURRENT = ModOptions.INSTANCE.DebugScreen;
        if (HudManager.CURRENT.Display) original.call(graphics);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}