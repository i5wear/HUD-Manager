package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.client.renderer.state.gui.pip.OversizedItemRenderState;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PictureInPictureRenderer.class)
public abstract class PictureInPictureRendererMixin {

    @WrapMethod(method = "blitTexture")
    private void modifyExtraState(PictureInPictureRenderState renderState, GuiRenderState guiRenderState, Operation<Void> original) {
        HudManager.CURRENT = switch (renderState) {
            case PictureInPictureRenderState output when HudManager.CONTENT.containsKey(output) -> HudManager.CONTENT.get(output);
            case OversizedItemRenderState output when HudManager.CONTENT.containsKey(output.guiItemRenderState()) -> HudManager.CONTENT.get(output.guiItemRenderState());
            default -> HudManager.DEFAULT;
        };
        original.call(renderState, guiRenderState);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}