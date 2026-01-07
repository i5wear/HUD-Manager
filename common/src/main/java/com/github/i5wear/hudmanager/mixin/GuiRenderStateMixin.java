package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiRenderState.class)
public abstract class GuiRenderStateMixin {

    @ModifyVariable(method = "submitPicturesInPictureState", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private PictureInPictureRenderState storeMiscElement(PictureInPictureRenderState original) {
        HudManager.STORED_RESIZER.put(original, HudManager.CURRENT_MANAGER.Resizer);
        HudManager.STORED_OPACITY.put(original, HudManager.CURRENT_MANAGER.Opacity);
        return original;
    }
}