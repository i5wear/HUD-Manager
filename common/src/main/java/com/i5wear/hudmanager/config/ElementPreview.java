package com.i5wear.hudmanager.config;

import com.i5wear.hudmanager.Manager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class ElementPreview extends AbstractWidget {

    private final Manager Identity;
    private final int[] Property;

    public ElementPreview(Manager Identity, int... Property) {
        super(0, 0, 0, 0, Component.literal("abc"));
        this.Identity = Identity;
        this.Property = Property;
    }

    @Override protected void updateWidgetNarration(NarrationElementOutput output) { output.add(NarratedElementType.TITLE, getMessage()); }

    @Override protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        setSize(Identity.Scale.get() * Property[0] / 50, Identity.Scale.get() * Property[1] / 50);
        setX(Identity.Scale.get() * (Property[2] - Property[0]) / 100 + (Identity.OffsetX.get() + Property[4]) * graphics.guiWidth() / 100);
        setY(Identity.Scale.get() * (Property[3] - Property[1]) / 100 + (Identity.OffsetY.get() + Property[5]) * graphics.guiHeight() / 100);
        graphics.fill(getX(), getY(), getRight(), getBottom(), Manager.modifyColor(0x7FFFFFFF, Identity.Opacity.get()));
    }
}