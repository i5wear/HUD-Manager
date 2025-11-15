package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SubtitleOverlay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SubtitleOverlay.class)
public abstract class SubtitleOverlayMixin {

    @WrapMethod(method = "render")
    private void modifyClosedCaption(GuiGraphics graphics, Operation<Void> original) {
        if (Manager.CLOSED_CAPTION.apply(graphics))
            original.call(graphics);
        Manager.reset(graphics);
    }
}