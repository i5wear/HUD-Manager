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
 * <p> The simplest config screen implementation. </p>
 * <p> Converts arbitrary {@link Object} to a config screen by reflection, enters with {@link ModOptions#INSTANCE}. </p>
 * <p> Supports {@link Number}, {@link String}, {@link Boolean} and {@link Enum} types, or their collections and combinations. </p>
 * <p> Auto-Generates translatable with {@link #NAMESPACE}, {@link Field#getName()} or {@link Enum#name()}, and suffixes. </p>
 * <p> Requires JSON format for ease of serialization and deserialization, but with lenient user input. </p>
 *
 * @see ModOptions
 * @author i5wear
 */
public class ModOptionsScreen extends OptionsSubScreen {

    public static String NAMESPACE = "hudmanager.options";

    protected final List<AbstractWidget> Storage = new ArrayList<>();

    protected final List<Runnable> onClose = new ArrayList<>();

    @Override public void removed() { ModOptions.save(); }

    @Override protected void addOptions() { super.list.addSmall(Storage); }

    public static Component translate(String... input) { return Component.translatableWithFallback(String.join(".", input), ""); }

    public ModOptionsScreen(Screen parent) { this(parent, ModOptions.INSTANCE, translate(NAMESPACE)); }

    public ModOptionsScreen(Object ignore, Screen parent) { this(parent, ModOptions.INSTANCE, translate(NAMESPACE)); }

    public ModOptionsScreen(Screen parent, Object source, Component title) {
        super(parent, Minecraft.getInstance().options, title);
        for (var clazz = source.getClass(); !clazz.equals(Object.class); clazz = clazz.getSuperclass()) {
            if (ModOptions.ADAPTER.excluder().excludeClass(clazz, true)) return;
            for (var field : clazz.getDeclaredFields()) {
                if (ModOptions.ADAPTER.excluder().excludeField(field, true)) continue;
                Storage.addLast(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, translate(NAMESPACE, field.getName()), super.font));
                Storage.getLast().setTooltip(Tooltip.create(translate(NAMESPACE, field.getName(), "tooltip")));
                Storage.addLast(construct(field, source, translate(NAMESPACE, field.getName())));
                Storage.getLast().setTooltip(Tooltip.create(translate(NAMESPACE, field.getName(), "tooltip")));
            }
        }
    }

    protected AbstractWidget construct(Field field, Object source, Component title) {
        field.setAccessible(true);
        var GETTER = Failable.asSupplier(Failable.apply(MethodHandles.lookup()::unreflectGetter, field).bindTo(source)::invoke);
        var SETTER = Failable.asConsumer(Failable.apply(MethodHandles.lookup()::unreflectSetter, field).bindTo(source)::invoke);
        if (ModOptions.ADAPTER.getAdapter(field.getType()) instanceof ReflectiveTypeAdapterFactory.Adapter)
            return Button.builder(Component.translatable("menu.options"), ignore -> Minecraft.getInstance().setScreen(new ModOptionsScreen(this, GETTER.get(), title))).build();
        var output = ModOptions.ADAPTER.toJson(GETTER.get(), field.getGenericType());
        onClose.addLast(() -> SETTER.accept(ModOptions.ADAPTER.fromJson(output, field.getGenericType())));
        if (field.getType() == boolean.class || field.getType() == Boolean.class)
            return CycleButton.builder(input -> input.equals(Boolean.TRUE) ? Component.translatable("gui.yes") : Component.translatable("gui.no"), GETTER.get())
                .withValues(Boolean.TRUE, Boolean.FALSE).displayOnlyValue().create(title, (ignore, input) -> SETTER.accept(input));
        if (field.getType().isEnum() && field.getType().getEnumConstants().length < 8)
            return CycleButton.builder(input -> translate(NAMESPACE, Enum.class.cast(input).name()), GETTER.get())
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

    @Override protected void addFooter() {
        onClose.addLast(this::onClose);
        var layout = super.layout.addToFooter(LinearLayout.horizontal().spacing(Button.DEFAULT_SPACING));
        layout.addChild(Button.builder(Component.translatable("gui.back"), ignore -> onClose.forEach(Runnable::run)).build());
        layout.addChild(Button.builder(Component.translatable("gui.done"), ignore -> onClose.getLast().run()).build());
    }
}