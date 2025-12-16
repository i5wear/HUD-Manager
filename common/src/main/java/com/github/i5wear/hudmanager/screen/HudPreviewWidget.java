package com.github.i5wear.hudmanager.screen;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;
import net.minecraft.network.chat.Component;

public class HudPreviewWidget extends AbstractWidget {

    private final Screen Target;
    private final HudManager Identity;
    private final int[] Property;

    private MouseButtonEvent MouseOrigin = null;
    private MouseButtonEvent MouseCurrent = null;

    public HudPreviewWidget(Screen parent, HudManager Identity, int... Property) {
        super(0, 0, 0, 0, Component.literal("abc"));
        Target = new ModOptionsScreen(parent, Identity, super.getMessage());
        this.Identity = Identity;
        this.Property = Property;
    }

    @Override protected boolean isValidClickButton(MouseButtonInfo ignore) { return true; }

    @Override public void onClick(MouseButtonEvent event, boolean bl) {
        switch(event.button()) {
            case 0:
                MouseOrigin = event;
                MouseCurrent = event;
                break;
            case 1:
                Minecraft.getInstance().setScreen(Target);
                break;
            case 2:
                Identity.Resizer = 1;
                Identity.Opacity = 1;
                Identity.OffsetX = 0;
                Identity.OffsetY = 0;
                break;
        }
    }

    @Override public void onDrag(MouseButtonEvent event, double d, double e) { MouseCurrent = event; }

    @Override public void onRelease(MouseButtonEvent event) {
        Identity.OffsetX += (float) (MouseCurrent.x() - MouseOrigin.x());
        Identity.OffsetY += (float) (MouseCurrent.y() - MouseOrigin.y());
        MouseOrigin = null; MouseCurrent = null;
    }

    @Override protected void updateWidgetNarration(NarrationElementOutput output) { output.add(NarratedElementType.TITLE, super.getMessage()); }

    @Override protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        active = Identity.Display; visible = Identity.Display;
        setSize((int)(2 * Identity.Resizer * Property[0]), (int)(2 * Identity.Resizer * Property[1]));
        setX((int)(Identity.OffsetX + Identity.Resizer * (Property[2] - Property[0]) + Property[4]));
        setY((int)(Identity.OffsetY + Identity.Resizer * (Property[3] - Property[1]) + Property[5]));
        if (MouseOrigin != null && MouseCurrent != null)
            setPosition(getX() + (int)(MouseCurrent.x() - MouseOrigin.x()), getY() + (int)(MouseCurrent.y() - MouseOrigin.y()));
        graphics.fill(super.getX(), super.getY(), super.getRight(), super.getBottom(), 0xFFFFFFFF);
    }
}