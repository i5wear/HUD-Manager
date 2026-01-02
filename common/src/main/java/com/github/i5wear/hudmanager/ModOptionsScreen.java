package com.github.i5wear.hudmanager;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import org.apache.commons.lang3.function.Failable;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> A simple config screen implementation. </p>
 * <p> Converts arbitrary object to a config screen by recursive reflection, enter with {@link ModOptions#INSTANCE}. </p>
 * <p> Supports {@link Number}, {@link String}, {@link Boolean} and {@link Enum} types, or their collections and combinations. </p>
 * <p> To make comments, define a {@link Component} field in {@link ModOptions} and its content and ordinal matters. </p>
 * <p> Requires JSON format for ease of serialization and deserialization, but with lenient user input. </p>
 *
 * @see ModOptions
 * @author i5wear
 */
public class ModOptionsScreen extends OptionsSubScreen {

    public static final String NAMESPACE = "hudmanager.options";

    protected final List<Runnable> onBack = new ArrayList<>();

    protected final List<AbstractWidget> Content = new ArrayList<>();

    @Override protected void addOptions() { super.list.addSmall(Content); }

    @Override protected void addFooter() {
        var layout = super.layout.addToFooter(LinearLayout.horizontal().spacing(Button.DEFAULT_SPACING));
        layout.addChild(Button.builder(Component.translatable("gui.back"), ignore -> { onBack.forEach(Runnable::run); super.onClose(); }).build());
        layout.addChild(Button.builder(Component.translatable("gui.done"), ignore -> { ModOptions.save(); super.onClose(); }).build());
    }

    public static Component translate(String... input) { return Component.translatableWithFallback(String.join(".", input), ""); }

    public ModOptionsScreen(Screen parent) { this(parent, ModOptions.INSTANCE, translate(NAMESPACE, "title")); }

    public ModOptionsScreen(Object ignore, Screen parent) { this(parent, ModOptions.INSTANCE, translate(NAMESPACE, "title")); }

    public ModOptionsScreen(Screen parent, Object target, Component title) {
        super(parent, Minecraft.getInstance().options, title);
        for (var clazz = target.getClass(); !clazz.equals(Object.class); clazz = clazz.getSuperclass()) {
            if (ModOptions.ADAPTER.excluder().excludeClass(clazz, true)) return;
            for (var field : clazz.getDeclaredFields()) {
                if (ModOptions.ADAPTER.excluder().excludeField(field, true)) continue;
                Content.addLast(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, translate(NAMESPACE, field.getName()), super.font));
                Content.getLast().setTooltip(Tooltip.create(translate(NAMESPACE, field.getName(), "tooltip")));
                Content.addLast(construct(field, target, translate(NAMESPACE, field.getName())));
                Content.getLast().setTooltip(Tooltip.create(translate(NAMESPACE, field.getName(), "tooltip")));
            }
        }
    }

    protected AbstractWidget construct(Field field, Object target, Component title) {
        field.setAccessible(true);
        var GETTER = Failable.asSupplier(Failable.apply(MethodHandles.lookup()::unreflectGetter, field).bindTo(target)::invoke);
        var SETTER = Failable.asConsumer(Failable.apply(MethodHandles.lookup()::unreflectSetter, field).bindTo(target)::invoke);
        var output = ModOptions.ADAPTER.toJson(GETTER.get(), field.getGenericType());
        if (Component.class.isAssignableFrom(field.getType()))
            return new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, (Component) GETTER.get(), super.font);
        if (ModOptions.ADAPTER.getAdapter(field.getType()) instanceof ReflectiveTypeAdapterFactory.Adapter)
            return Button.builder(Component.translatable("menu.options"), ignore -> Minecraft.getInstance().setScreen(new ModOptionsScreen(this, GETTER.get(), title))).build();
        onBack.addLast(() -> SETTER.accept(ModOptions.ADAPTER.fromJson(output, field.getGenericType())));
        if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class))
            return CycleButton.onOffBuilder(GETTER.get().equals(true)).displayOnlyValue().create(title, (ignore, input) -> SETTER.accept(input));
        if (field.getType().isEnum() && field.getType().getEnumConstants().length <= 8)
            return CycleButton.builder(input -> translate(NAMESPACE, "const", ((Enum<?>) input).name()), GETTER.get())
                .withValues(field.getType().getEnumConstants()).displayOnlyValue().create(title, (ignore, input) -> SETTER.accept(input));
        var widget = new EditBox(super.font, Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, title);
        widget.setMaxLength(Integer.MAX_VALUE);
        widget.setResponder(input -> {
            widget.setTextColor(EditBox.DEFAULT_TEXT_COLOR);
            try { SETTER.accept(ModOptions.ADAPTER.fromJson(input, field.getGenericType())); }
            catch (Exception ignore) { widget.setTextColor(0xFFFF3333); }
        });
        widget.setValue(output);
        return widget;
    }
}