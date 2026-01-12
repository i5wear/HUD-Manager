package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = Window.class, priority = 1001) // Temporary Noxesium Compat
public abstract class WindowMixin {

    @WrapMethod(method = "getGuiScaledWidth")
    private int storeElementAxisX(Operation<Integer> original) { return Math.round(original.call() / HudManager.CURRENT.Resizer); }

    @WrapMethod(method = "getGuiScaledHeight")
    private int storeElementAxisY(Operation<Integer> original) { return Math.round(original.call() / HudManager.CURRENT.Resizer); }

}