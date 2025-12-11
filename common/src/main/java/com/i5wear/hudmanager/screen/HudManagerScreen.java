package com.i5wear.hudmanager.screen;

import com.i5wear.hudmanager.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;

public class HudManagerScreen extends OptionsSubScreen {

    private final HudManager Options;

    public HudManagerScreen(Screen screen, HudManager options, Component title) {
        super(screen, Minecraft.getInstance().options, title);
        Options = options;
    }

    @Override protected void addOptions() {
        makeEntry(Options.Visible, Component.translatable("hudmanager.Visible"));
        makeEntry(Options.Preview, Component.translatable("hudmanager.Preview"));
        makeEntry(Options.Resizer, Component.translatable("hudmanager.Resizer"));
        makeEntry(Options.Opacity, Component.translatable("hudmanager.Opacity"));
        makeEntry(Options.OffsetX, Component.translatable("hudmanager.OffsetX"));
        makeEntry(Options.OffsetY, Component.translatable("hudmanager.OffsetY"));
    }

    private void makeEntry(MutableBoolean target, Component title) {
        var widget0 = new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, title, super.getFont());
        var widget1 = CycleButton.onOffBuilder(target.getValue()).displayOnlyValue().create(title, (ignore, input) -> target.setValue(input));
        super.list.addSmall(widget0, widget1);
    }

    private void makeEntry(Mutable<Number> target, Component title) {
        var widget0 = new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, title, super.getFont());
        var widget1 = new EditBox(super.getFont(), Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, title);
        widget1.setValue(NumberFormat.getPercentInstance().format(target.getValue()));
        widget1.setResponder(input -> onEdited(target, input));
        super.list.addSmall(widget0, widget1);
    }

    private void onEdited(Mutable<Number> target, String input) {
        try { target.setValue(NumberFormat.getPercentInstance().parse(input)); }
        catch (Exception e) { LoggerFactory.getLogger("hudmanager").warn(e.getMessage()); }
    }
}