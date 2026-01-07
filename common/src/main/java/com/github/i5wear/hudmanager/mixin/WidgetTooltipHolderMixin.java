package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.ModOptions;
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
            Math.round(original.position().x() / ModOptions.INSTANCE.Tooltip.Resizer),
            Math.round(original.position().y() / ModOptions.INSTANCE.Tooltip.Resizer),
            Math.round(original.width() / ModOptions.INSTANCE.Tooltip.Resizer),
            Math.round(original.height() / ModOptions.INSTANCE.Tooltip.Resizer)
        );
    }
}