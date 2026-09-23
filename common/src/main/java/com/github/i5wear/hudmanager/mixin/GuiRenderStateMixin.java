package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.render.HudManager;
import com.github.i5wear.hudmanager.render.Transformer;
import net.minecraft.client.renderer.state.gui.BlitRenderState;
import net.minecraft.client.renderer.state.gui.GuiElementRenderState;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiRenderState.class)
public abstract class GuiRenderStateMixin {

    @ModifyVariable(method = "addPicturesInPictureState", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private PictureInPictureRenderState storeCustomState(PictureInPictureRenderState original) {
        if (HudManager.CURRENT != HudManager.DEFAULT)
            HudManager.CONTENT.put(original, HudManager.CURRENT);
        return original;
    }

    @ModifyVariable(method = "addBlitToCurrentLayer", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private BlitRenderState modifyCustomState(BlitRenderState original) {
        return new BlitRenderState(
            original.pipeline(), original.textureSetup(), HudManager.CURRENT.apply(original.pose()),
            original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
            ARGB.srgbLerp(HudManager.CURRENT.Opacity, 0, original.color()), original.scissorArea()
        );
    }

    @ModifyVariable(method = "addGuiElement", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private GuiElementRenderState modifyElementState(GuiElementRenderState original) { return Transformer.render(original); }

}