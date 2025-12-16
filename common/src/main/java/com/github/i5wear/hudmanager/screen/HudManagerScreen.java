package com.github.i5wear.hudmanager.screen;

import com.github.i5wear.hudmanager.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HudManagerScreen extends Screen {

    private final Screen Parent;

    @Override public void removed() { ModOptions.save(); }

    @Override public void onClose() { Minecraft.getInstance().setScreen(Parent); }

    public HudManagerScreen(Screen parent) { super(Component.empty()); Parent = parent; }

    public HudManagerScreen(Object ignore, Screen parent) { this(parent); } // For NeoForge

    @Override public void init() {
        super.addRenderableOnly(new StringWidget(Component.literal("AAAAAA"), super.getFont()));
        super.addRenderableWidget(new HudPreviewWidget(this, ModOptions.INSTANCE.ActionBar, 50, 5, 0, -68, 0.5f, 1));
    }
}