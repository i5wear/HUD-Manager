package com.github.i5wear.hudmanager.common.mixin;

import com.github.i5wear.hudmanager.common.render.HudRenderer;
import net.minecraft.client.renderer.state.gui.GuiElementRenderState;
import net.minecraft.client.renderer.state.gui.GuiItemRenderState;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiRenderState.class)
public abstract class GuiRenderStateMixin {

    @ModifyVariable(method = "addItem", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private GuiItemRenderState modifyCustomState(GuiItemRenderState original) { return HudRenderer.render(original); }

    @ModifyVariable(method = "addPicturesInPictureState", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private PictureInPictureRenderState modifyCustomState(PictureInPictureRenderState original) { return HudRenderer.render(original); }

    @ModifyVariable(method = "addGuiElement", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private GuiElementRenderState modifyElementState(GuiElementRenderState original) { return HudRenderer.render(original); }

}