package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiRenderState.class)
public abstract class GuiRenderStateMixin {

    @WrapMethod(method = "submitPicturesInPictureState")
    private void storeExtraElement(PictureInPictureRenderState input, Operation<Void> original) {
        if (HudManager.CURRENT != HudManager.DEFAULT)
            HudManager.STORAGE.put(input, HudManager.CURRENT);
        original.call(input);
    }
}