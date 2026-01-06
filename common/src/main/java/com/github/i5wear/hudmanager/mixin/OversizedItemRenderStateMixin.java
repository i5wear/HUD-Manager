package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiItemRenderState;
import net.minecraft.client.gui.render.state.pip.OversizedItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(OversizedItemRenderState.class)
public abstract class OversizedItemRenderStateMixin {

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 0, argsOnly = true)
    private GuiItemRenderState storeItemStates(GuiItemRenderState original) {
        HudManager.STORED_RESIZER.put(this, HudManager.STORED_RESIZER.getOrDefault(original, 1f));
        HudManager.STORED_OPACITY.put(this, HudManager.STORED_OPACITY.getOrDefault(original, 1f));
        return original;
    }
}