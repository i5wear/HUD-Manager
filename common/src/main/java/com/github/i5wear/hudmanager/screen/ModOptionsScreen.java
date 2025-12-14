package com.github.i5wear.hudmanager.screen;

import com.github.i5wear.hudmanager.ModOptions;
import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import org.apache.commons.lang3.function.Failable;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ModOptionsScreen extends OptionsSubScreen {

    protected final List<AbstractWidget> Content = new ArrayList<>();

    @Override public void removed() { ModOptions.save(); }

    @Override protected void addOptions() { super.list.addSmall(Content); }

    protected static Component translate(String... path) { return Component.translatable(String.join(".", path)); }

    public ModOptionsScreen(Screen parent) { this(parent, ModOptions.INSTANCE, Component.translatable("hudmanager")); } // Fabric

    public ModOptionsScreen(Object ignore, Screen parent) { this(parent, ModOptions.INSTANCE, Component.translatable("hudmanager")); } // NeoForge

    public ModOptionsScreen(Screen parent, Object target, Component title) {
        super(parent, Minecraft.getInstance().options, title);
        for (var entry : target.getClass().getFields()) {
            entry.setAccessible(true);
            var subtitle = translate("hudmanager", entry.getName());
            if (!Modifier.isStatic(entry.getModifiers())) {
                Content.add(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, subtitle, super.getFont()));
                Content.getLast().setTooltip(Tooltip.create(translate("hudmanager", entry.getName(), "tooltip")));
                Content.add(Failable.call(() -> makeWidget(entry, target, subtitle)));
            }
        }
    }

    protected AbstractWidget makeWidget(Field entry, Object target, Component title) throws IllegalAccessException {
        var GETTER = Failable.asSupplier(MethodHandles.lookup().unreflectGetter(entry).bindTo(target)::invoke);
        var SETTER = Failable.asConsumer(MethodHandles.lookup().unreflectSetter(entry).bindTo(target)::invoke);
        if (entry.getType().equals(boolean.class))
            return CycleButton.onOffBuilder((boolean) GETTER.get()).displayOnlyValue().create(title, (ignore, input) -> SETTER.accept(input));
        else if (entry.getType().isEnum())
            return CycleButton.onOffBuilder((boolean) GETTER.get()).displayOnlyValue().create(title, (ignore, input) -> SETTER.accept(input));
        else if (entry.getType().isPrimitive() || List.class.isAssignableFrom(entry.getType())) {
            var widget = new EditBox(super.getFont(), Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, title);
            widget.setValue(new Gson().toJson(GETTER.get(), entry.getType()));
            widget.setResponder(input -> SETTER.accept(new Gson().fromJson(input, entry.getType())));
            return widget;
        }
        return Button.builder(Component.translatable("menu.options"), ignore -> Minecraft.getInstance().setScreen(new ModOptionsScreen(this, GETTER.get(), title))).build();
    }
}