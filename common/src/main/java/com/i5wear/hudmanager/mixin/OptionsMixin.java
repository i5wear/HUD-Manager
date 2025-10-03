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

    @ModifyReturnValue(method = "showAutosaveIndicator", at = @At("RETURN"))
    public OptionInstance<Boolean> modifyAutosaveIndicatorShow(OptionInstance<Boolean> original) {
        if (original.get() != Config.AUTOSAVE_INDICATOR.Show.get()) {
            original.set(!Global.AutosaveIndicatorFlag);
            Config.AUTOSAVE_INDICATOR.Show.set(!Global.AutosaveIndicatorFlag);
            Global.AutosaveIndicatorFlag = !Global.AutosaveIndicatorFlag;
        }
        return original;
    }

    @ModifyReturnValue(method = "showSubtitles", at = @At("RETURN"))
    public OptionInstance<Boolean> modifyClosedCaptionShow(OptionInstance<Boolean> original) {
        if (original.get() != Config.CLOSED_CAPTION.Show.get()) {
            original.set(!Global.ClosedCaptionFlag);
            Config.CLOSED_CAPTION.Show.set(!Global.ClosedCaptionFlag);
            Global.ClosedCaptionFlag = !Global.ClosedCaptionFlag;
        }
        return original;
    }
}