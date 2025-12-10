package com.i5wear.hudmanager.screen;

import com.i5wear.hudmanager.HudOptions;
import com.i5wear.hudmanager.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;

public class HudOptionsScreen extends OptionsSubScreen {

    public static final Component TITLE = Component.translatable("hudmanager");

    public HudOptionsScreen(Screen LastScreen) { super(LastScreen, Minecraft.getInstance().options, TITLE); }

    protected void makeEntry(HudManager Target, Component Title) {
        super.list.addSmall(
            new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Title, super.getFont()),
            Button.builder(Component.translatable("menu.options"), ignore -> Minecraft.getInstance().setScreen(new HudManagerScreen(this, Target, Title))).build()
        );
    }

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
        makeEntry(HudOptions.INSTANCE.Toast, Component.translatable("hudmanager.Toast"));
        makeEntry(HudOptions.INSTANCE.Tooltip, Component.translatable("hudmanager.Tooltip"));
    }
}