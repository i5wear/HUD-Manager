package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Options.class)
public class OptionsMixin {

    @ModifyReturnValue(method = "showSubtitles", at = @At("RETURN"))
    public OptionInstance<Boolean> modifySubtitlesShow(OptionInstance<Boolean> original)
    { original.set(HudManager.ClosedCaptionShow.getAsBoolean()); return original; }

    @ModifyReturnValue(method = "showAutosaveIndicator", at = @At("RETURN"))
    public OptionInstance<Boolean> modifyAutosaveIndicatorShow(OptionInstance<Boolean> original)
    { original.set(HudManager.AutoSaveIndicatorShow.getAsBoolean()); return original; }

}