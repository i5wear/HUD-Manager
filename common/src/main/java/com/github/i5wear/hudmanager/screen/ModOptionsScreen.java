package com.github.i5wear.hudmanager.screen;

import com.github.i5wear.hudmanager.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ModOptionsScreen extends OptionsSubScreen {

    private static final String NAMESPACE = "hudmanager.options";

    private final List<AbstractWidget> Content = new ArrayList<>();

    @Override protected void addOptions() { super.list.addSmall(Content); }

    @Override public void onClose() { ModOptions.save(); super.onClose(); }

    public static Component translate(CharSequence... input) { return Component.translatable(String.join(".", input)); }

    public ModOptionsScreen(Screen parent) { this(parent, ModOptions.INSTANCE, translate(NAMESPACE, "title")); }

    public ModOptionsScreen(Object ignore, Screen parent) { this(parent, ModOptions.INSTANCE, translate(NAMESPACE, "title")); }

    public ModOptionsScreen(Screen parent, Object target, Component title) {
        super(parent, Minecraft.getInstance().options, title);
        for (var field : FieldUtils.getAllFields(target.getClass())) {
            if ((field.getModifiers() & (Modifier.STATIC | Modifier.TRANSIENT)) != 0) continue;
            Content.add(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, translate(NAMESPACE, field.getName()), super.font));
            Content.getLast().setTooltip(Tooltip.create(translate(NAMESPACE, field.getName(), "tooltip")));
            Content.add(construct(field, target, translate(NAMESPACE, field.getName())));
        }
    }

    private AbstractWidget construct(Field field, Object target, Component title) {
        field.setAccessible(true);
        var GETTER = Failable.asSupplier(Failable.apply(MethodHandles.lookup()::unreflectGetter, field).bindTo(target)::invoke);
        var SETTER = Failable.asConsumer(Failable.apply(MethodHandles.lookup()::unreflectSetter, field).bindTo(target)::invoke);
        if (ClassUtils.isAssignable(field.getType(), Boolean.class))
            return CycleButton.onOffBuilder(GETTER.get().equals(true)).displayOnlyValue().create(title, (ignore, input) -> SETTER.accept(input));
        if (ClassUtils.isAssignable(field.getType(), Enum.class))
            return CycleButton.builder(input -> translate(NAMESPACE, "enums", Enum.class.cast(input).name()), GETTER.get())
                .withValues(field.getType().getEnumConstants()).displayOnlyValue().create(title, (ignore, input) -> SETTER.accept(input));
        if (Stream.of(Number.class, CharSequence.class, Iterable.class).anyMatch(clazz -> ClassUtils.isAssignable(field.getType(), clazz))) {
            var widget = new EditBox(super.font, Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, title);
            widget.setValue(ModOptions.READER.toJson(GETTER.get(), field.getGenericType())); // Don't Format
            widget.setResponder(
                input -> {
                    widget.setTextColor(EditBox.DEFAULT_TEXT_COLOR);
                    try { SETTER.accept(ModOptions.READER.fromJson(input, field.getGenericType())); }
                    catch (Exception ignore) { widget.setTextColor(ARGB.scaleRGB(EditBox.DEFAULT_TEXT_COLOR, 0.5f)); }
                }
            );
            return widget;
        }
        return Button.builder(Component.translatable("menu.options"), ignore -> Minecraft.getInstance().setScreen(new ModOptionsScreen(this, GETTER.get(), title))).build();
    }
}