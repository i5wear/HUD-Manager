package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HUDManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Unique static private void HUDManager_scaleHudElement(float scale, GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        arg0.pose().pushMatrix();
        HUDManager.SCALE = scale;
        arg0.pose().scale(scale);
        original.call(arg0, arg1);
        HUDManager.SCALE = 1;
        arg0.pose().popMatrix();
    }

    @ModifyArg(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lorg/joml/Matrix3x2fStack;translate(FF)Lorg/joml/Matrix3x2f;", remap = false), index = 1)
    public float relocateActionBar(float original) { return 0.75f * original + 50; }

    @WrapMethod(method = "renderOverlayMessage")
    private void scaleActionBar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.Config.ActionBarScale.getAsInt(), arg0, arg1, original); }

    @WrapMethod(method = "renderSavingIndicator")
    private void scaleAutoSaveIndicator(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.Config.AutoSaveIndicatorScale.getAsInt(), arg0, arg1, original); }

    @WrapMethod(method = "renderBossOverlay")
    private void scaleBossBar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.Config.BossBarScale.getAsInt(), arg0, arg1, original); }

    @WrapMethod(method = "renderSubtitleOverlay")
    private void scaleClosedCaption(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.Config.ClosedCaptionScale.getAsInt(), arg0, arg1, original); }

    @WrapMethod(method = "renderCrosshair")
    private void scaleCrosshair(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.Config.CrosshairScale.getAsInt(), arg0, arg1, original); }

    @WrapMethod(method = "renderDebugOverlay")
    private void scaleDebugScreen(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.Config.DebugScreenScale.getAsInt(), arg0, arg1, original); }

    @WrapMethod(method = "renderTabList")
    private void scalePlayerList(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.Config.PlayerListScale.getAsInt(), arg0, arg1, original); }

    @WrapMethod(method = "renderScoreboardSidebar")
    private void scaleScoreboardSidebar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.Config.ScoreboardSidebarScale.getAsInt(), arg0, arg1, original); }

    @WrapMethod(method = "renderTitle")
    private void scaleScreenTitle(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.Config.ScreenTitleScale.getAsInt(), arg0, arg1, original); }

    @WrapMethod(method = "renderEffects")
    private void scaleStatusEffect(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.Config.StatusEffectScale.getAsInt(), arg0, arg1, original); }

}