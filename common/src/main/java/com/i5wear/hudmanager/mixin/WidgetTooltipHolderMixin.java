package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import net.minecraft.client.gui.components.WidgetTooltipHolder;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WidgetTooltipHolder.class)
public abstract class WidgetTooltipHolderMixin {

    @ModifyVariable(method = "createTooltipPositioner", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private ScreenRectangle storeWidgetTooltipAxis(ScreenRectangle original) {
        return new ScreenRectangle(
            Manager.modifyVector(original.position().x(), Manager.TOOLTIP.Resizer.get()),
            Manager.modifyVector(original.position().y(), Manager.TOOLTIP.Resizer.get()),
            Manager.modifyVector(original.width(), Manager.TOOLTIP.Resizer.get()),
            Manager.modifyVector(original.height(), Manager.TOOLTIP.Resizer.get())
        );
    }
}