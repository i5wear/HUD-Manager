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

    // Uniform Interface Layer of GUI scale & offset.
    @Unique private static void modifyHudElement(int scale, int x, int y, GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        arg0.pose().pushMatrix();
        HudManager.Temp.SCALE = scale;
        arg0.pose().scale(0.01f * scale);
        arg0.pose().translate(x, y);
        original.call(arg0, arg1);
        HudManager.Temp.SCALE = 100;
        arg0.pose().popMatrix();
    }

    @WrapMethod(method = "renderOverlayMessage")
    private void modifyActionBar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.ActionBarScale.get();
        if (scale == 0 || HudManager.ActionBarShow.get() == false) return;
        int x = HudManager.ActionBarOffsetX.get();
        int y = HudManager.ActionBarOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderSavingIndicator")
    private void modifyAutoSaveIndicator(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.AutoSaveIndicatorScale.get();
        if (scale == 0 || !HudManager.AutoSaveIndicatorShow.get()) return;
        int x = HudManager.AutoSaveIndicatorOffsetX.get();
        int y = HudManager.AutoSaveIndicatorOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderBossOverlay")
    private void modifyBossBar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.BossBarScale.get();
        if (scale == 0 || !HudManager.BossBarShow.get()) return;
        int x = HudManager.BossBarOffsetX.get();
        int y = HudManager.BossBarOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderSubtitleOverlay")
    private void modifyClosedCaption(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.ClosedCaptionScale.get();
        if (scale == 0 || !HudManager.ClosedCaptionShow.get()) return;
        int x = HudManager.ClosedCaptionOffsetX.get();
        int y = HudManager.ClosedCaptionOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderCrosshair")
    private void modifyCrosshair(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.CrosshairScale.get();
        if (scale == 0 || !HudManager.CrosshairShow.get()) return;
        int x = HudManager.CrosshairOffsetX.get();
        int y = HudManager.CrosshairOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderDebugOverlay")
    private void modifyDebugScreen(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.DebugScreenScale.get();
        if (scale == 0 || !HudManager.DebugScreenShow.get()) return;
        int x = HudManager.DebugScreenOffsetX.get();
        int y = HudManager.DebugScreenOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    }

    /* This only works on Fabric. This method may be deleted in Neoforge.
    @WrapMethod(method = "renderHotbarAndDecorations")
    private void modifyHotbar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.HotbarScale.get();
        if (scale == 0 || !HudManager.HotbarShow.get()) return;
        int x = HudManager.HotbarOffsetX.get();
        int y = HudManager.HotbarOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    } */

    @WrapMethod(method = "renderTabList")
    private void modifyPlayerList(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.PlayerListScale.get();
        if (scale == 0 || !HudManager.PlayerListShow.get()) return;
        int x = HudManager.PlayerListOffsetX.get();
        int y = HudManager.PlayerListOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderScoreboardSidebar")
    private void modifyScoreboardSidebar(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.ScoreboardSidebarScale.get();
        if (scale == 0 || !HudManager.ScoreboardSidebarShow.get()) return;
        int x = HudManager.ScoreboardSidebarOffsetX.get();
        int y = HudManager.ScoreboardSidebarOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderTitle")
    private void modifyScreenTitle(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.ScreenTitleScale.get();
        if (scale == 0 || !HudManager.ScreenTitleShow.get()) return;
        int x = HudManager.ScreenTitleOffsetX.get();
        int y = HudManager.ScreenTitleOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    }

    @WrapMethod(method = "renderEffects")
    private void modifyStatusEffect(GuiGraphics arg0, DeltaTracker arg1, Operation<Void> original) {
        int scale = HudManager.StatusEffectScale.get();
        if (scale == 0 || !HudManager.StatusEffectShow.get()) return;
        int x = HudManager.StatusEffectOffsetX.get();
        int y = HudManager.StatusEffectOffsetY.get();
        modifyHudElement(scale, x, y, arg0, arg1, original);
    }
}