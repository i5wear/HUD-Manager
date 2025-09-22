package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HUDManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

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

    @WrapMethod(method = "renderOverlayMessage")
    private void scaleActionBar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.CONFIGURATION.ActionBarScale, arg0, arg1, original); }

    @WrapMethod(method = "renderSavingIndicator")
    private void scaleAutoSaveIndicator(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.CONFIGURATION.AutoSaveIndicatorScale, arg0, arg1, original); }

    @WrapMethod(method = "renderBossOverlay")
    private void scaleBossBar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.CONFIGURATION.BossBarScale, arg0, arg1, original); }

    @WrapMethod(method = "renderSubtitleOverlay")
    private void scaleClosedCaption(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.CONFIGURATION.ClosedCaptionScale, arg0, arg1, original); }

    @WrapMethod(method = "renderCrosshair")
    private void scaleCrosshair(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.CONFIGURATION.CrosshairScale, arg0, arg1, original); }

    @WrapMethod(method = "renderDebugOverlay")
    private void scaleDebugScreen(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.CONFIGURATION.DebugScreenScale, arg0, arg1, original); }

    @WrapMethod(method = "renderTabList")
    private void scalePlayerList(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.CONFIGURATION.PlayerListScale, arg0, arg1, original); }

    @WrapMethod(method = "renderScoreboardSidebar")
    private void scaleScoreboardSidebar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.CONFIGURATION.ScoreboardSidebarScale, arg0, arg1, original); }

    @WrapMethod(method = "renderTitle")
    private void scaleScreenTitle(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.CONFIGURATION.ScreenTitleScale, arg0, arg1, original); }

    @WrapMethod(method = "renderEffects")
    private void scaleStatusEffect(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original)
    { HUDManager_scaleHudElement(0.01f * HUDManager.CONFIGURATION.StatusEffectScale, arg0, arg1, original); }

}