package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @WrapMethod(method = "renderTooltipInternal")
    private void modifyTooltip(Font arg0, List<?> arg1, int x, int y, ClientTooltipPositioner arg2, ResourceLocation arg3, Operation<Void> original) {
        x = Manager.modifyVector(x, Manager.TOOLTIP.Scale.get());
        y = Manager.modifyVector(y, Manager.TOOLTIP.Scale.get());
        if (Manager.TOOLTIP.apply((GuiGraphics)(Object)(this)))
            original.call(arg0, arg1, x, y, arg2, arg3);
        Manager.reset((GuiGraphics)(Object)(this));
    }
}