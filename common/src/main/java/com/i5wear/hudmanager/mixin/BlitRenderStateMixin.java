package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import net.minecraft.client.gui.render.state.BlitRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlitRenderState.class)
public abstract class BlitRenderStateMixin {

    @ModifyVariable(method = "<init>*", at = @At("CTOR_HEAD"), ordinal = 4, argsOnly = true)
    private int modifyTextureAlpha(int Original) { return ARGB.color(Math.min(ARGB.alpha(Original) * Manager.CURRENT_ALPHA / 100, 255), Original); }

}