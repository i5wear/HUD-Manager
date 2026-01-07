package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.pip.OversizedItemRenderer;
import net.minecraft.client.gui.render.state.pip.OversizedItemRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.IntSupplier;

@Mixin(OversizedItemRenderer.class)
public abstract class OversizedItemRendererMixin {

    @ModifyVariable(method = "blitTexture", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private OversizedItemRenderState storeOversizeItemState(OversizedItemRenderState original) {
        HudManager.STORED_RESIZER.put(original, HudManager.CURRENT_RESIZER);
        HudManager.STORED_OPACITY.put(original, ARGB.alphaFloat(IntSupplier.class.cast(original.guiItemRenderState()).getAsInt()));
        return original;
    }
}