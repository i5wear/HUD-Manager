package com.github.i5wear.hudmanager.screen;

import com.github.i5wear.hudmanager.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HudManagerScreen extends Screen {

    public final Screen parent;

    public HudManagerScreen(Screen parent) {
        super(Component.empty());
        this.parent = parent;
    }

    public HudManagerScreen(Object ignore, Screen LastScreen) { this(LastScreen); }

    @Override public void init() {
        super.addRenderableOnly(new StringWidget(Component.literal("AAAAAA"), super.getFont()));
        super.addRenderableWidget(new HudPreviewWidget(this, ModOptions.INSTANCE.ActionBar, 50, 5, 0, -68, 50, 100));
    }

    @Override public void removed() { ModOptions.save(); }

    @Override public void onClose() { Minecraft.getInstance().setScreen(parent); }
}