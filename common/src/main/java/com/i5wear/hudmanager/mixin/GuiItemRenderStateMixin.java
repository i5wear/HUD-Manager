package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import net.minecraft.client.gui.render.state.GuiItemRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Supplier;

@Mixin(GuiItemRenderState.class)
public abstract class GuiItemRenderStateMixin implements Supplier<Integer> {

    @Unique public int STORED_COLOR = ARGB.color(Math.min(255 * Manager.CURRENT_OPACITY / 100, 255), -1);

    @Override public Integer get() { return STORED_COLOR; }

}