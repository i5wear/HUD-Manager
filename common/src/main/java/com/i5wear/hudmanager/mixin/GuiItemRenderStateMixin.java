package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.render.state.GuiItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.IntSupplier;

@Mixin(GuiItemRenderState.class)
public abstract class GuiItemRenderStateMixin implements IntSupplier {

    @Unique private final int STORED_COLOR = HudManager.recolor(0xFFFFFFFF, HudManager.CURRENT_OPACITY.getValue());

    @Override public int getAsInt() { return STORED_COLOR; }

}