package com.github.i5wear.hudmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> A simple config screen implementation. </p>
 * <p> Converts arbitrary object to a config screen by recursive reflection, like how Gson does. </p>
 * <p> Supports numeric, string, boolean and enum types, or their collections and combinations. </p>
 * <p> Requires JSON format for ease of serialization and deserialization, with lenient user input. </p>
 *
 * @see ModOptions
 * @author i5wear
 */
public class ModOptionsScreen extends OptionsSubScreen {

    public static final String NAMESPACE = "hudmanager.options";

    public static final Gson GSON = new GsonBuilder().setLenient().create();

    protected final List<AbstractWidget> Content = new ArrayList<>();

    @Override protected void addOptions() { super.list.addSmall(Content); }

    @Override public void onClose() { ModOptions.save(); super.onClose(); }

    public static Component translate(String... input) { return Component.translatable(String.join(".", input)); }

    public ModOptionsScreen(Screen parent) { this(parent, ModOptions.INSTANCE, translate(NAMESPACE, "title")); }

    public ModOptionsScreen(Object ignore, Screen parent) { this(parent, ModOptions.INSTANCE, translate(NAMESPACE, "title")); }

    public ModOptionsScreen(Screen parent, Object target, Component title) {
        super(parent, Minecraft.getInstance().options, title);
        for (var field : FieldUtils.getAllFields(target.getClass())) {
            if (GSON.excluder().excludeField(field, true)) continue;
            var name = GSON.fieldNamingStrategy().translateName(field);
            Content.add(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, translate(NAMESPACE, name), super.font));
            Content.getLast().setTooltip(Tooltip.create(translate(NAMESPACE, name, "tooltip")));
            Content.add(construct(field, target, translate(NAMESPACE, name)));
        }
    }

    protected AbstractWidget construct(Field field, Object target, Component title) {
        field.setAccessible(true);
        var GETTER = Failable.asSupplier(Failable.apply(MethodHandles.lookup()::unreflectGetter, field).bindTo(target)::invoke);
        var SETTER = Failable.asConsumer(Failable.apply(MethodHandles.lookup()::unreflectSetter, field).bindTo(target)::invoke);
        if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class))
            return CycleButton.onOffBuilder(GETTER.get().equals(true)).displayOnlyValue().create(title, (ignore, input) -> SETTER.accept(input));
        else if (field.getType().isEnum() && field.getType().getEnumConstants().length < 7)
            return CycleButton.builder(input -> translate(NAMESPACE, "enums", GSON.toJsonTree(input).getAsString()), GETTER.get())
                .withValues(field.getType().getEnumConstants()).displayOnlyValue().create(title, (ignore, input) -> SETTER.accept(input));
        else if (GSON.getAdapter(field.getType()) instanceof ReflectiveTypeAdapterFactory.Adapter)
            return Button.builder(Component.translatable("menu.options"), ignore -> Minecraft.getInstance().setScreen(new ModOptionsScreen(this, GETTER.get(), title))).build();
        else {
            var widget = new EditBox(super.font, Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, title);
            widget.setMaxLength(Integer.MAX_VALUE);
            widget.setValue(GSON.toJson(GETTER.get(), field.getGenericType()));
            widget.setResponder(input -> {
                widget.setTextColor(EditBox.DEFAULT_TEXT_COLOR);
                try { SETTER.accept(GSON.fromJson(input, field.getGenericType())); }
                catch (Exception ignore) { widget.setTextColor(0xFFFF5555); } // ChatFormatting.RED
            });
            return widget;
        }
    }
}