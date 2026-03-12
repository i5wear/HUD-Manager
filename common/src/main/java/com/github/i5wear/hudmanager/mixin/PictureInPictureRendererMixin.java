package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PictureInPictureRenderer.class)
public abstract class PictureInPictureRendererMixin {

    @WrapMethod(method = "blitTexture")
    private void modifyExtraState(PictureInPictureRenderState instance, GuiRenderState graphics, Operation<Void> original) {
        if (HudManager.CONTENT.containsKey(instance))
            HudManager.CURRENT = HudManager.CONTENT.get(instance);
        original.call(instance, graphics);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}