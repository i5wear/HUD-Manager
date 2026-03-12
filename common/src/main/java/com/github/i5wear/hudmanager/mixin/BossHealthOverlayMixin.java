package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.github.i5wear.hudmanager.ModOptions;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.BossHealthOverlay;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BossHealthOverlay.class)
public abstract class BossHealthOverlayMixin {

    @WrapMethod(method = "extractRenderState")
    private void modifyBossBar(GuiGraphicsExtractor graphics, Operation<Void> original) {
        HudManager.CURRENT = ModOptions.INSTANCE.BossBar;
        if (HudManager.CURRENT.Display)
            original.call(graphics);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}