package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiRenderState.class)
public abstract class GuiRenderStateMixin {

    @Inject(method = "submitPicturesInPictureState", at = @At("TAIL"))
    private void storeExtraElement(PictureInPictureRenderState instance, CallbackInfo original) { HudManager.CATEGORY.put(instance, HudManager.CURRENT); }

}