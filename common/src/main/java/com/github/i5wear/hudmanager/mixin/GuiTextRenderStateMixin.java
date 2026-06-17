package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.renderer.state.gui.GuiTextRenderState;
import net.minecraft.util.ARGB;
import org.joml.Matrix3x2fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiTextRenderState.class)
public class GuiTextRenderStateMixin {

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 0, argsOnly = true)
    private Matrix3x2fc modifyTextStateScale(Matrix3x2fc original) { return HudManager.CURRENT.apply(original); }

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 2, argsOnly = true)
    private int modifyTextStateColor1(int original) { return ARGB.multiplyAlpha(original, HudManager.CURRENT.Opacity); }

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 3, argsOnly = true)
    private int modifyTextStateColor2(int original) { return ARGB.multiplyAlpha(original, HudManager.CURRENT.Opacity); }

}
