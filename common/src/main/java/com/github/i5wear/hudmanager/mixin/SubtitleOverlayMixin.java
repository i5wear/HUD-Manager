package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.ModOptions;
import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SubtitleOverlay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SubtitleOverlay.class)
public abstract class SubtitleOverlayMixin {

    @WrapMethod(method = "render")
    private void modifyClosedCaption(GuiGraphics graphics, Operation<Void> original) {
        if (ModOptions.INSTANCE.ClosedCaption.apply(graphics.pose()))
            original.call(graphics);
        HudManager.reset(graphics.pose());
    }
}