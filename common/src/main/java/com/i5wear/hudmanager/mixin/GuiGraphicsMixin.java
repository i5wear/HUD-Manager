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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @ModifyVariable(method = "renderTooltipInternal", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int syncTooltipPosX(int original) { return 100 * original / Config.TOOLTIP.Size.get(); }

    @ModifyVariable(method = "renderTooltipInternal", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int syncTooltipPosY(int original) { return 100 * original / Config.TOOLTIP.Size.get(); }

    @WrapMethod(method = "renderTooltipInternal")
    private void modifyTooltip(Font arg0, List<ClientTooltipComponent> arg1, int PosX, int PosY, ClientTooltipPositioner arg2, ResourceLocation arg3, Operation<Void> original) {
        Config Value = Config.TOOLTIP;
        GuiGraphics instance = (GuiGraphics)(Object) this;
        if (Value.Show.get() && Value.Size.get() > 0) {
            Config.CURRENT_SIZE = Value.Size.get();
            instance.pose().pushPose();
            instance.pose().scale(0.01f * Value.Size.get(), 0.01f * Value.Size.get(), 1);
            instance.pose().translate(0.01f * Value.PosX.get() * instance.guiWidth(), 0.01f * Value.PosY.get() * instance.guiHeight(), 0);
            original.call(arg0, arg1, PosX, PosY, arg2, arg3);
            instance.pose().popPose();
            Config.CURRENT_SIZE = 100;
        }
    }

    @WrapMethod(method = "submitProfilerChartRenderState")
    private void modifyProfiler(List<ResultField> list, int PosX1, int PosY1, int PosX2, int PosY2, Operation<Void> original) {
        GuiGraphics instance = (GuiGraphics)(Object) this;
        PosX1 = Manager.DEBUG_SCREEN.Size.get() * (PosX1 + Manager.DEBUG_SCREEN.PosX.get() * instance.guiWidth() / 100) / 100;
        PosX2 = Manager.DEBUG_SCREEN.Size.get() * (PosX2 + Manager.DEBUG_SCREEN.PosX.get() * instance.guiWidth() / 100) / 100;
        PosY1 = Manager.DEBUG_SCREEN.Size.get() * (PosY1 + Manager.DEBUG_SCREEN.PosY.get() * instance.guiHeight() / 100) / 100;
        PosY2 = Manager.DEBUG_SCREEN.Size.get() * (PosY2 + Manager.DEBUG_SCREEN.PosY.get() * instance.guiHeight() / 100) / 100;
        original.call(list, PosX1, PosY1, PosX2, PosY2);
    }
}