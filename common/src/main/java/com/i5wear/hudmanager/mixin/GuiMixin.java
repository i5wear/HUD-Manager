package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Config;
import com.i5wear.hudmanager.Global;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @WrapMethod(method = "renderDebugOverlay")
    private void modifyDebugScreen(GuiGraphics arg0, Operation<Void> original) {
        Config Value = Config.DEBUG_SCREEN;
        if (Value.Size.get() == 0 || !Value.Show.get()) return;
        arg0.pose().pushMatrix();
        Global.CURRENT_SIZE = Value.Size.get();
        arg0.pose().scale(0.01f * Value.Size.get());
        arg0.pose().translate(Value.PosX.get(), Value.PosY.get());
        original.call(arg0);
        Global.CURRENT_SIZE = 100;
        arg0.pose().popMatrix();
    }
}