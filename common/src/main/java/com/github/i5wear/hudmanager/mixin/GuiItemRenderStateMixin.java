package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiItemRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.IntSupplier;

@Mixin(GuiItemRenderState.class)
public abstract class GuiItemRenderStateMixin implements IntSupplier {

    @Unique private final int STORED_COLOR = ARGB.white(HudManager.CURRENT_OPACITY);

    @Override public int getAsInt() { return STORED_COLOR; }

}