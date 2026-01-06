package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiItemRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.render.state.pip.OversizedItemRenderState;
import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiRenderState.class)
public abstract class GuiRenderStateMixin {

    @ModifyVariable(method = "submitItem", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private GuiItemRenderState storeItemStates(GuiItemRenderState original) {
        HudManager.STORED_RESIZER.put(original, 1f);
        HudManager.STORED_OPACITY.put(original, HudManager.CURRENT_OPACITY);
        return original;
    }

    @ModifyVariable(method = "submitPicturesInPictureState", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private PictureInPictureRenderState storeElementStates(PictureInPictureRenderState original) {
        if (original instanceof OversizedItemRenderState) return original;
        HudManager.STORED_RESIZER.put(original, HudManager.CURRENT_RESIZER);
        HudManager.STORED_OPACITY.put(original, HudManager.CURRENT_OPACITY);
        return original;
    }
}