package com.github.i5wear.hudmanager.screen;

import com.github.i5wear.hudmanager.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HudManagerScreen extends Screen {

    private final Screen Parent;

    @Override public void onClose() {
        ModOptions.save();
        Minecraft.getInstance().setScreen(Parent);
    }

    public HudManagerScreen(Object ignore, Screen parent) { this(parent); } // For NeoForge

    public HudManagerScreen(Screen parent) { super(Component.empty()); Parent = parent; }

    @Override public void init() {
        super.addRenderableWidget(Button.builder(Component.empty(), ignore -> Minecraft.getInstance().setScreen(new ModOptionsScreen(this))).build());
        super.addRenderableWidget(new HudPreviewWidget(this, ModOptions.INSTANCE.ActionBar, 50, 5, 0, -68, width / 2, height));
    }
}