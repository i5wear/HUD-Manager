package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import net.minecraft.client.gui.render.state.ColoredRectangleRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ColoredRectangleRenderState.class)
public abstract class ColoredRectangleRenderStateMixin {

    @ModifyVariable(method = "<init>*", at = @At("CTOR_HEAD"), ordinal = 4, argsOnly = true)
    private int modifyBackgroundAlpha1(int Original) { return ARGB.color(Math.min(ARGB.alpha(Original) * Manager.CURRENT_ALPHA / 100, 255), Original); }

    @ModifyVariable(method = "<init>*", at = @At("CTOR_HEAD"), ordinal = 5, argsOnly = true)
    private int modifyBackgroundAlpha2(int Original) { return ARGB.color(Math.min(ARGB.alpha(Original) * Manager.CURRENT_ALPHA / 100, 255), Original); }

}