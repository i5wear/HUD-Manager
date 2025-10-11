package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.gui.render.state.pip.GuiProfilerChartRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiProfilerChartRenderState.class)
public abstract class GuiProfilerChartRenderStateMixin {

    @ModifyReturnValue(method = "scale", at = @At("TAIL"))
    private float syncProfilerChartSize(float Original) { return Original * Manager.DEBUG_SCREEN.Size.get() / 100; }

}