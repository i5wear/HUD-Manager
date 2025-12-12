package com.github.i5wear.hudmanager.screen;

import com.github.i5wear.hudmanager.HudOptions;
import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;

public class HudOptionsScreen extends OptionsSubScreen {

    private static final Component TITLE = Component.translatable("hudmanager");
    private static final Component ENTRY = Component.translatable("menu.options");

    public HudOptionsScreen(Screen screen) { super(screen, Minecraft.getInstance().options, TITLE); }

    @Override public void removed() { HudOptions.save(); }

    @Override protected void addOptions() {
        makeEntry(HudOptions.INSTANCE.ActionBar, Component.translatable("hudmanager.ActionBar"));
        makeEntry(HudOptions.INSTANCE.BossBar, Component.translatable("hudmanager.BossBar"));
        makeEntry(HudOptions.INSTANCE.ClosedCaption, Component.translatable("hudmanager.ClosedCaption"));
        makeEntry(HudOptions.INSTANCE.Crosshair, Component.translatable("hudmanager.Crosshair"));
        makeEntry(HudOptions.INSTANCE.DebugScreen, Component.translatable("hudmanager.DebugScreen"));
        makeEntry(HudOptions.INSTANCE.HotbarGroup, Component.translatable("hudmanager.HotbarGroup"));
        makeEntry(HudOptions.INSTANCE.PlayerList, Component.translatable("hudmanager.PlayerList"));
        makeEntry(HudOptions.INSTANCE.Scoreboard, Component.translatable("hudmanager.Scoreboard"));
        makeEntry(HudOptions.INSTANCE.ScreenTitle, Component.translatable("hudmanager.ScreenTitle"));
        makeEntry(HudOptions.INSTANCE.StatusEffect, Component.translatable("hudmanager.StatusEffect"));
        makeEntry(HudOptions.INSTANCE.ToastMessage, Component.translatable("hudmanager.ToastMessage"));
        makeEntry(HudOptions.INSTANCE.Tooltip, Component.translatable("hudmanager.Tooltip"));
    }

    private void makeEntry(HudManager target, Component title) {
        super.list.addSmall(
            new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, title, super.getFont()),
            Button.builder(ENTRY, ignore -> Minecraft.getInstance().setScreen(new HudPreviewScreen(this, target, title))).build()
        );
    }
}