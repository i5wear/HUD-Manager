package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Config;
import com.i5wear.hudmanager.Global;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Options.class)
public abstract class OptionsMixin {

    @ModifyReturnValue(method = "showSubtitles", at = @At("RETURN"))
    public OptionInstance<Boolean> syncClosedCaptionShow(OptionInstance<Boolean> original) {
        if (original.get() != Config.CLOSED_CAPTION.Show.get()) {
            original.set(!Global.CLOSED_CAPTION_FLAG);
            Config.CLOSED_CAPTION.Show.set(!Global.CLOSED_CAPTION_FLAG);
            Global.CLOSED_CAPTION_FLAG = !Global.CLOSED_CAPTION_FLAG;
        }
        return original;
    }
}