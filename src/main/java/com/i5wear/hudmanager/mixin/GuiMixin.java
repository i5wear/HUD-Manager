package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Unique static private void HudManager_scaleHudElement(int scale, int x, int y, GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        arg0.pose().pushMatrix();
        HudManager.Global.SCALE = scale;
        arg0.pose().scale(0.01f * scale);
        arg0.pose().translate(x, y);
        original.call(arg0, arg1);
        HudManager.Global.SCALE = 100;
        arg0.pose().popMatrix();
    }

    @WrapMethod(method = "renderOverlayMessage")
    private void scaleActionBar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.ActionBarScale.getAsInt();
        if (scale == 0 || HudManager.ActionBarShow.isFalse()) return;
        int x = HudManager.ActionBarOffsetX.getAsInt();
        int y = HudManager.ActionBarOffsetY.getAsInt();
        HudManager_scaleHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderSavingIndicator")
    private void scaleAutoSaveIndicator(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.AutoSaveIndicatorScale.getAsInt();
        if (scale == 0 || HudManager.AutoSaveIndicatorShow.isFalse()) return;
        int x = HudManager.AutoSaveIndicatorOffsetX.getAsInt();
        int y = HudManager.AutoSaveIndicatorOffsetY.getAsInt();
        HudManager_scaleHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderBossOverlay")
    private void scaleBossBar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.BossBarScale.getAsInt();
        if (scale == 0 || HudManager.BossBarShow.isFalse()) return;
        int x = HudManager.BossBarOffsetX.getAsInt();
        int y = HudManager.BossBarOffsetY.getAsInt();
        HudManager_scaleHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderSubtitleOverlay")
    private void scaleClosedCaption(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.ClosedCaptionScale.getAsInt();
        if (scale == 0 || HudManager.ClosedCaptionShow.isFalse()) return;
        int x = HudManager.ClosedCaptionOffsetX.getAsInt();
        int y = HudManager.ClosedCaptionOffsetY.getAsInt();
        HudManager_scaleHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderCrosshair")
    private void scaleCrosshair(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.CrosshairScale.getAsInt();
        if (scale == 0 || HudManager.CrosshairShow.isFalse()) return;
        int x = HudManager.CrosshairOffsetX.getAsInt();
        int y = HudManager.CrosshairOffsetY.getAsInt();
        HudManager_scaleHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderDebugOverlay")
    private void scaleDebugScreen(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.DebugScreenScale.getAsInt();
        if (scale == 0 || HudManager.DebugScreenShow.isFalse()) return;
        int x = HudManager.DebugScreenOffsetX.getAsInt();
        int y = HudManager.DebugScreenOffsetY.getAsInt();
        HudManager_scaleHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderTabList")
    private void scalePlayerList(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.PlayerListScale.getAsInt();
        if (scale == 0 || HudManager.PlayerListShow.isFalse()) return;
        int x = HudManager.PlayerListOffsetX.getAsInt();
        int y = HudManager.PlayerListOffsetY.getAsInt();
        HudManager_scaleHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderScoreboardSidebar")
    private void scaleScoreboardSidebar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.ScoreboardSidebarScale.getAsInt();
        if (scale == 0 || HudManager.ScoreboardSidebarShow.isFalse()) return;
        int x = HudManager.ScoreboardSidebarOffsetX.getAsInt();
        int y = HudManager.ScoreboardSidebarOffsetY.getAsInt();
        HudManager_scaleHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderTitle")
    private void scaleScreenTitle(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.ScreenTitleScale.getAsInt();
        if (scale == 0 || HudManager.ScreenTitleShow.isFalse()) return;
        int x = HudManager.ScreenTitleOffsetX.getAsInt();
        int y = HudManager.ScreenTitleOffsetY.getAsInt();
        HudManager_scaleHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderEffects")
    private void scaleStatusEffect(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.StatusEffectScale.getAsInt();
        if (scale == 0 || HudManager.StatusEffectShow.isFalse()) return;
        int x = HudManager.StatusEffectOffsetX.getAsInt();
        int y = HudManager.StatusEffectOffsetY.getAsInt();
        HudManager_scaleHudElement(scale, x, y, arg0, arg1, original);
    }

}