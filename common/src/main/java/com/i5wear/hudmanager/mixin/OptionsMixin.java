package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Config;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Options.class)
public abstract class OptionsMixin {

    @Unique private static Boolean PREVIOUS_SHOW = false;

    @ModifyReturnValue(method = "showSubtitles", at = @At("RETURN"))
    private OptionInstance<Boolean> syncClosedCaptionShow(OptionInstance<Boolean> original) {
        if (original.get() != Config.CLOSED_CAPTION.Show.get()) {
            original.set(!PREVIOUS_SHOW);
            Config.CLOSED_CAPTION.Show.set(!PREVIOUS_SHOW);
            PREVIOUS_SHOW = !PREVIOUS_SHOW;
        }
        return original;
    }
}