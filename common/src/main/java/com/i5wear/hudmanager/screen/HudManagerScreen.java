package com.i5wear.hudmanager.screen;

import com.i5wear.hudmanager.HudOptions;
import com.i5wear.hudmanager.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;

public class HudManagerScreen extends OptionsSubScreen {

    private final HudManager Target;

    public HudManagerScreen(Screen LastScreen, HudManager Target, Component Title) {
        super(LastScreen, Minecraft.getInstance().options, Title);
        this.Target = Target;
    }

    @Override protected void addOptions() {
        super.list.addSmall(
            new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.OffsetX"), font),
            new EditBox(font, Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.OffsetY"))
        );
    }

    @Override public void removed() {
        HudOptions.save();
    }
}