package com.github.i5wear.hudmanager.screen;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;

public class HudManagerScreen extends OptionsSubScreen {

    private final HudManager Target;

    public HudManagerScreen(Screen screen, HudManager target, Component title) {
        super(screen, Minecraft.getInstance().options, title);
        Target = target;
    }

    @Override protected void addOptions() {
        var widgets = new ArrayList<AbstractWidget>();
        widgets.add(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.Display"), super.getFont()));
        widgets.add(CycleButton.onOffBuilder(Target.Display).displayOnlyValue().create(Component.translatable("hudmanager.Display"), (ignore, input) -> Target.Display = input));
        widgets.add(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.Resizer"), super.getFont()));
        widgets.add(new EditBox(super.getFont(), Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.Resizer")));
        ((EditBox) widgets.getLast()).setValue(String.valueOf(Target.Resizer));
        ((EditBox) widgets.getLast()).setResponder(input -> Target.Resizer = NumberUtils.toFloat(input, 1));
        widgets.add(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.Opacity"), super.getFont()));
        widgets.add(new EditBox(super.getFont(), Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.Opacity")));
        ((EditBox) widgets.getLast()).setValue(String.valueOf(Target.Opacity));
        ((EditBox) widgets.getLast()).setResponder(input -> Target.Opacity = NumberUtils.toFloat(input, 1));
        widgets.add(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.OffsetX"), super.getFont()));
        widgets.add(new EditBox(super.getFont(), Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.OffsetX")));
        ((EditBox) widgets.getLast()).setValue(String.valueOf(Target.OffsetX));
        ((EditBox) widgets.getLast()).setResponder(input -> Target.OffsetX = NumberUtils.toFloat(input, 0));
        widgets.add(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.OffsetY"), super.getFont()));
        widgets.add(new EditBox(super.getFont(), Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, Component.translatable("hudmanager.OffsetY")));
        ((EditBox) widgets.getLast()).setValue(String.valueOf(Target.OffsetY));
        ((EditBox) widgets.getLast()).setResponder(input -> Target.OffsetY = NumberUtils.toFloat(input, 0));
        super.list.addSmall(widgets);
    }
}