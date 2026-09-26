package com.github.i5wear.hudmanager.common.mixin;

import com.github.i5wear.hudmanager.common.render.HudManager;
import net.minecraft.client.renderer.state.gui.GuiTextRenderState;
import net.minecraft.util.ARGB;
import org.joml.Matrix3x2fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiTextRenderState.class)
public class GuiTextRenderStateMixin {

    @Unique private final HudManager CONTENT = HudManager.CURRENT;

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 2, argsOnly = true)
    private int modifyTextStateColor1(int original) { return ARGB.multiplyAlpha(original, HudManager.CURRENT.Opacity); }

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 3, argsOnly = true)
    private int modifyTextStateColor2(int original) { return ARGB.multiplyAlpha(original, HudManager.CURRENT.Opacity); }

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 0, argsOnly = true)
    private Matrix3x2fc modifyTextStateScale(Matrix3x2fc original) { return HudManager.CURRENT.apply(original); }

    @ModifyArg(method = "ensurePrepared", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/navigation/ScreenRectangle;transformMaxBounds(Lorg/joml/Matrix3x2fc;)Lnet/minecraft/client/gui/navigation/ScreenRectangle;"), index = 0)
    private Matrix3x2fc modifyTextStateBound(Matrix3x2fc original) { return CONTENT.reset(original); }

}
