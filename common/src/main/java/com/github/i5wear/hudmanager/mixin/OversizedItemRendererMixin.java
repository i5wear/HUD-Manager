package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.render.pip.OversizedItemRenderer;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.client.renderer.state.gui.pip.OversizedItemRenderState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OversizedItemRenderer.class)
public abstract class OversizedItemRendererMixin {

    @WrapMethod(method = "blitTexture")
    private void modifyOversizeItemState(OversizedItemRenderState instance, GuiRenderState graphics, Operation<Void> original) {
        if (HudManager.CONTENT.containsKey(instance.guiItemRenderState()))
            HudManager.CURRENT = HudManager.CONTENT.get(instance.guiItemRenderState());
        original.call(instance, graphics);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}