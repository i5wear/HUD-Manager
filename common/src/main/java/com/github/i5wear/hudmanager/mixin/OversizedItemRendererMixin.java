package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.pip.OversizedItemRenderer;
import net.minecraft.client.gui.render.state.pip.OversizedItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(OversizedItemRenderer.class)
public abstract class OversizedItemRendererMixin {

    @ModifyVariable(method = "blitTexture", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private OversizedItemRenderState storeOversizeItemState(OversizedItemRenderState original) {
        HudManager.STORED_RESIZER.put(original, HudManager.STORED_RESIZER.getOrDefault(original.guiItemRenderState(), 1f));
        HudManager.STORED_OPACITY.put(original, HudManager.STORED_OPACITY.getOrDefault(original.guiItemRenderState(), 1f));
        return original;
    }
}