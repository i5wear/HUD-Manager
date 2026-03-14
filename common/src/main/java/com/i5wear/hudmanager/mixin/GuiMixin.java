package com.i5wear.hudmanager.mixin;

import com.i5wear.hudmanager.Manager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Gui.class)
public class GuiMixin {

    @WrapMethod(method = "renderOverlayMessage")
    private void modifyActionBar(GuiGraphics graphics, DeltaTracker tracker, Operation<Void> original) {
        if (Manager.ACTION_BAR.apply(graphics))
            original.call(graphics, tracker);
        Manager.reset(graphics);
    }

    @WrapMethod(method = "renderCrosshair")
    private void modifyCrosshair(GuiGraphics graphics, DeltaTracker tracker, Operation<Void> original) {
        if (Manager.CROSSHAIR.apply(graphics))
            original.call(graphics, tracker);
        Manager.reset(graphics);
    }

    @WrapMethod(method = "renderHotbarAndDecorations")
    private void modifyHotbarGroup(GuiGraphics graphics, DeltaTracker tracker, Operation<Void> original) {
        if (Manager.HOTBAR_GROUP.apply(graphics))
            original.call(graphics, tracker);
        Manager.reset(graphics);
    }

    @WrapMethod(method = "renderTabList")
    private void modifyPlayerList(GuiGraphics graphics, DeltaTracker tracker, Operation<Void> original) {
        if (Manager.PLAYER_LIST.apply(graphics))
            original.call(graphics, tracker);
        Manager.reset(graphics);
    }

    @WrapMethod(method = "renderScoreboardSidebar")
    private void modifyScoreboard(GuiGraphics graphics, DeltaTracker tracker, Operation<Void> original) {
        if (Manager.SCOREBOARD.apply(graphics))
            original.call(graphics, tracker);
        Manager.reset(graphics);
    }

    @WrapMethod(method = "renderTitle")
    private void modifyScreenTitle(GuiGraphics graphics, DeltaTracker tracker, Operation<Void> original) {
        if (Manager.SCREEN_TITLE.apply(graphics))
            original.call(graphics, tracker);
        Manager.reset(graphics);
    }

    @WrapMethod(method = "renderEffects")
    private void modifyStatusEffect(GuiGraphics graphics, DeltaTracker tracker, Operation<Void> original) {
        if (Manager.STATUS_EFFECT.apply(graphics))
            original.call(graphics, tracker);
        Manager.reset(graphics);
    }
}