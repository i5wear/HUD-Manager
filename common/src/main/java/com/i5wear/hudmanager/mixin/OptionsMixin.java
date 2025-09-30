package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Options.class)
public abstract class OptionsMixin {

    @Unique private static boolean AutosaveIndicatorFlag = HudManager.AutoSaveIndicatorShow.get();
    @Unique private static boolean ClosedCaptionFlag = HudManager.ClosedCaptionShow.get();

    @ModifyReturnValue(method = "showAutosaveIndicator", at = @At("RETURN"))
    public OptionInstance<Boolean> modifyAutosaveIndicatorShow(OptionInstance<Boolean> original) {
        if (original.get() != HudManager.AutoSaveIndicatorShow.get()) {
            original.set(!AutosaveIndicatorFlag);
            HudManager.AutoSaveIndicatorShow.set(!AutosaveIndicatorFlag);
            AutosaveIndicatorFlag = !AutosaveIndicatorFlag;
        }
        return original;
    }

    @ModifyReturnValue(method = "showSubtitles", at = @At("RETURN"))
    public OptionInstance<Boolean> modifySubtitlesShow(OptionInstance<Boolean> original) {
        if (original.get() != HudManager.ClosedCaptionShow.get()) {
            original.set(!ClosedCaptionFlag);
            HudManager.ClosedCaptionShow.set(!ClosedCaptionFlag);
            ClosedCaptionFlag = !ClosedCaptionFlag;
        }
        return original;
    }
}