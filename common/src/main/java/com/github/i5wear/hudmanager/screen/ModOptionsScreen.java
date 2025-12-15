package com.github.i5wear.hudmanager.screen;

import com.github.i5wear.hudmanager.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ModOptionsScreen extends OptionsSubScreen {

    private static final String NAMESPACE = "hudmanager.options";

    private final List<AbstractWidget> Content = new ArrayList<>();

    @Override public void removed() { ModOptions.save(); }

    @Override protected void addOptions() { super.list.addSmall(Content); }

    private static Component translate(String... path) { return Component.translatable(String.join(".", path)); }

    public ModOptionsScreen(Screen parent) { this(parent, ModOptions.INSTANCE, "title"); }

    public ModOptionsScreen(Screen parent, Object target, String title) {
        super(parent, Minecraft.getInstance().options, translate(NAMESPACE, title));
        for (var field : FieldUtils.getAllFields(target.getClass())) {
            field.setAccessible(true);
            if (!Modifier.isStatic(field.getModifiers())) {
                Content.add(new StringWidget(Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, translate(NAMESPACE, field.getName()), super.getFont()));
                Content.getLast().setTooltip(Tooltip.create(translate(NAMESPACE, field.getName(), "tooltip")));
                Content.add(Failable.call(() -> construct(field, target)));
            }
        }
    }

    private AbstractWidget construct(Field field, Object target) throws Exception {
        var GETTER = Failable.asSupplier(MethodHandles.lookup().unreflectGetter(field).bindTo(target)::invoke);
        var SETTER = Failable.asConsumer(MethodHandles.lookup().unreflectSetter(field).bindTo(target)::invoke);
        if (field.getType().isEnum())
            return CycleButton.builder(input -> translate(NAMESPACE, Enum.class.cast(input).name()), GETTER.get())
                .withValues(field.getType().getEnumConstants()).displayOnlyValue()
                .create(translate(NAMESPACE, field.getName()), (ignore, input) -> SETTER.accept(input));
        if (ClassUtils.isAssignable(field.getType(), Boolean.class))
            return CycleButton.onOffBuilder((boolean) GETTER.get()).displayOnlyValue()
                .create(translate(NAMESPACE, field.getName()), (ignore, input) -> SETTER.accept(input));
        if (Stream.of(Number.class, CharSequence.class, Iterable.class).anyMatch(clazz -> ClassUtils.isAssignable(field.getType(), clazz))) {
            var widget = new EditBox(super.getFont(), Button.DEFAULT_WIDTH, Button.DEFAULT_HEIGHT, translate(NAMESPACE, field.getName()));
            widget.setValue(ModOptions.READER.toJson(GETTER.get(), field.getType())); // Don't Format
            widget.setResponder(ignore -> SETTER.accept(deserialize(widget, GETTER.get())));
            return widget;
        }
        return Button.builder(Component.translatable("menu.options"), ignore -> Minecraft.getInstance().setScreen(new ModOptionsScreen(this, GETTER.get(), field.getName()))).build();
    }

    private static Object deserialize(EditBox target, Object fallback) {
        target.setTextColor(0xFFFFFFFF);
        if (!target.getValue().isBlank())
            try { return ModOptions.READER.fromJson(target.getValue(), fallback.getClass()); }
            catch (Exception ignore) { target.setTextColor(0xFFFF0000); }
        return fallback;
    }
}