package com.i5wear.hudmanager.config;

import com.i5wear.hudmanager.HudManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class ElementPreview extends AbstractWidget {

    private final Screen Parent;
    private final HudManager Identity;
    private final int[] Property;

    private MouseButtonEvent MouseOrigin = null;
    private MouseButtonEvent MouseCurrent = null;

    public ElementPreview(Screen Parent, HudManager Identity, int... Property) {
        super(0, 0, 0, 0, Component.literal("abc"));
        this.Parent = Parent;
        this.Identity = Identity;
        this.Property = Property;
    }

    @Override public boolean mouseClicked(MouseButtonEvent event, boolean bl) {
        if (isMouseOver(event.x(), event.y())) {
            if (event.button() == 0)
                MouseOrigin = event;
            return true;
        }
        else return false;
    }

    @Override public void onDrag(MouseButtonEvent event, double d, double e) { MouseCurrent = event; }

    @Override public void onRelease(MouseButtonEvent event) { MouseOrigin = null; MouseCurrent = null; }

    @Override protected void updateWidgetNarration(NarrationElementOutput output) { output.add(NarratedElementType.TITLE, getMessage()); }

    @Override protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        update();
        graphics.fill(getX(), getY(), getRight(), getBottom(), HudManager.recolor(0x7FFFFFFF, Identity.Opacity));
    }

    private void update() {
        setSize(Identity.Resizer * Property[0] / 50, Identity.Resizer * Property[1] / 50);
        setX(Identity.Resizer * (Property[2] - Property[0]) / 100 + (Identity.OffsetX + Property[4]) * Parent.width / 100);
        setY(Identity.Resizer * (Property[3] - Property[1]) / 100 + (Identity.OffsetY + Property[5]) * Parent.height / 100);
        if (MouseOrigin != null && MouseCurrent != null)
            setPosition(getX() + (int)(MouseCurrent.x() - MouseOrigin.x()), getY() + (int)(MouseCurrent.y() - MouseOrigin.y()));
    }
}