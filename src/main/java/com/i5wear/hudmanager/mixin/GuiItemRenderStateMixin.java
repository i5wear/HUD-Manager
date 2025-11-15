package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import net.minecraft.client.gui.render.state.GuiItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Supplier;

@Mixin(GuiItemRenderState.class)
public abstract class GuiItemRenderStateMixin implements Supplier<Integer> {

    @Unique private final int STORED_COLOR = Manager.modifyColor(0xFFFFFFFF, Manager.CURRENT_OPACITY);

    @Override public Integer get() { return STORED_COLOR; }

}