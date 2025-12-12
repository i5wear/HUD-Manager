package com.github.i5wear.hudmanager.screen;

import com.github.i5wear.hudmanager.HudManager;
import com.github.i5wear.hudmanager.HudOptions;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HudPreviewScreen extends Screen {

    public final Screen LastScreen;

    public HudPreviewScreen(Screen LastScreen, HudManager target, Component title) {
        super(Component.empty());
        this.LastScreen = LastScreen;
    }

    @Override public void init() {
        super.addRenderableOnly(new StringWidget(Component.literal("AAAAAA"), super.getFont()));
        super.addRenderableWidget(new HudPreviewWidget(this, HudOptions.INSTANCE.ActionBar, 50, 5, 0, -68, 50, 100));
    }

    @Override public void onClose() { super.minecraft.setScreen(LastScreen); }
}