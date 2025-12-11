package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiTextRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiTextRenderState.class)
public abstract class GuiTextRenderStateMixin {

    @ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), ordinal = 2, argsOnly = true)
    private int storeTextColor(int original) { return HudManager.recolor(original, HudManager.CURRENT_OPACITY.getValue()); }

}