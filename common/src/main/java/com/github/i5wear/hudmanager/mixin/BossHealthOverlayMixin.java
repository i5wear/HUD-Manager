package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.github.i5wear.hudmanager.ModOptions;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.BossHealthOverlay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BossHealthOverlay.class)
public abstract class BossHealthOverlayMixin {

    @WrapMethod(method = "render")
    private void modifyBossBar(GuiGraphics graphics, Operation<Void> original) {
        if (ModOptions.INSTANCE.BossBar.apply(graphics.pose()))
            original.call(graphics);
        HudManager.reset(graphics.pose());
    }
}