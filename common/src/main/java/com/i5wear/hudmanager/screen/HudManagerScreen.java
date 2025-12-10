package com.i5wear.hudmanager.screen;

import com.i5wear.hudmanager.HudConfig;
import com.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HudManagerScreen extends Screen {

    private final Screen LastScreen;
    private final HudManager Target;

    protected HudManagerScreen(Screen LastScreen, HudManager Target) {
        super(Component.translatable(Target.Path));
        this.LastScreen = LastScreen;
        this.Target = Target;
    }

    @Override public void init() {
        Button Visible = Button.builder(Component.translatable(Target.Path + ".Visible"), button -> Target.Visible = !Target.Visible).bounds(40, 40, 120, 20).build();
    }

    @Override public void removed() { HudConfig.save(); }

    @Override public void onClose() { super.minecraft.setScreen(LastScreen); }

}