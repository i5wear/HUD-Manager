package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiItemRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import org.apache.commons.lang3.math.NumberUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiRenderState.class)
public abstract class GuiRenderStateMixin {

    @ModifyVariable(method = "submitItem", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private GuiItemRenderState storeItemState(GuiItemRenderState original) {
        HudManager.STORED_RESIZER.put(original, NumberUtils.FLOAT_ONE);
        HudManager.STORED_OPACITY.put(original, HudManager.CURRENT_OPACITY);
        return original;
    }

    @ModifyVariable(method = "submitPicturesInPictureState", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private PictureInPictureRenderState storeElementState(PictureInPictureRenderState original) {
        HudManager.STORED_RESIZER.put(original, HudManager.CURRENT_RESIZER);
        HudManager.STORED_OPACITY.put(original, HudManager.CURRENT_OPACITY);
        return original;
    }
}