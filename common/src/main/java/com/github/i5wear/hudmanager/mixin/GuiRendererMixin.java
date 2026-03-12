package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.render.GuiItemAtlas;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.renderer.state.gui.GuiItemRenderState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiRenderer.class)
public abstract class GuiRendererMixin {

    @WrapMethod(method = "submitBlitFromItemAtlas")
    private void modifyItemState(GuiItemRenderState instance, GuiItemAtlas.SlotView slotview, Operation<Void> original) {
        if (HudManager.CONTENT.containsKey(instance))
            HudManager.CURRENT = HudManager.CONTENT.get(instance);
        original.call(instance, slotview);
        HudManager.CURRENT = HudManager.DEFAULT;
    }
}