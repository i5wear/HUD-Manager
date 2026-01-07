package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiItemRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.IntSupplier;

@Mixin(GuiItemRenderState.class)
public class GuiItemRenderStateMixin implements IntSupplier {

    @Unique private final int STORED_COLOR = ARGB.srgbLerp(HudManager.CURRENT_OPACITY, 0, -1); // Patch #27

    @Override public int getAsInt() { return STORED_COLOR; }

}