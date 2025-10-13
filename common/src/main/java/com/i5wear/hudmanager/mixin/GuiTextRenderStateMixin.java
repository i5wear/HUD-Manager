package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import net.minecraft.client.gui.render.state.GuiTextRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiTextRenderState.class)
public abstract class GuiTextRenderStateMixin {

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 2, argsOnly = true)
    private int syncTextTextureAlpha(int original) { return ARGB.color(Math.min(ARGB.alpha(original) * Manager.CURRENT_ALPHA / 100, 255), original); }

}