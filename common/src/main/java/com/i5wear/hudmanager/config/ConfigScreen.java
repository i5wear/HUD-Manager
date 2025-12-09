package com.i5wear.hudmanager.config;

import com.i5wear.hudmanager.HudConfig;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {

    public final Screen Parent;

    public ConfigScreen(Screen Parent) {
        super(Component.empty());
        this.Parent = Parent;
    }

    @Override public void init() {
        super.addRenderableWidget(new ElementPreview(this, HudConfig.INSTANCE.ActionBar, 50, 5, 0, -68, 50, 100));
    }

    @Override public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override public void onClose() { super.minecraft.setScreen(Parent); }
}