package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiTextRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiTextRenderState.class)
public abstract class GuiTextRenderStateMixin {

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 2, argsOnly = true)
    private int storeTextColor1(int original) { return ARGB.multiplyAlpha(original, HudManager.CURRENT_OPACITY); }

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 3, argsOnly = true)
    private int storeTextColor2(int original) { return ARGB.multiplyAlpha(original, HudManager.CURRENT_OPACITY); }

}