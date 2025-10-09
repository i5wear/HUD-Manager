package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @WrapMethod(method = "renderTooltipInternal")
    private void modifyTooltip(Font arg0, List<ClientTooltipComponent> arg1, int PosX, int PosY, ClientTooltipPositioner arg2, ResourceLocation arg3, Operation<Void> Original) {
        GuiGraphics Instance = (GuiGraphics)(Object) this;
        PosX = 100 * PosX / Manager.TOOLTIP.Size.get();
        PosY = 100 * PosY / Manager.TOOLTIP.Size.get();
        if (Manager.TOOLTIP.apply(Instance))
            Original.call(arg0, arg1, PosX, PosY, arg2, arg3);
        Instance.pose().popPose();
        Manager.CURRENT_SIZE = 100;
    }
}