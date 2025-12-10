package com.i5wear.hudmanager.screen;

import com.i5wear.hudmanager.HudOptions;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HudPreviewScreen extends Screen {

    public final Screen LastScreen;

    public HudPreviewScreen(Screen LastScreen) {
        super(Component.empty());
        this.LastScreen = LastScreen;
    }

    @Override public void init() {
        super.addRenderableWidget(new HudPreviewWidget(this, HudOptions.INSTANCE.ActionBar, 50, 5, 0, -68, 50, 100));
    }

    @Override public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override public void onClose() { super.minecraft.setScreen(LastScreen); }
}