package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import net.minecraft.client.renderer.state.gui.*;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiRenderState.class)
public abstract class GuiRenderStateMixin {

    @ModifyVariable(method = "addBlitToCurrentLayer", ordinal = 0, argsOnly = true, at = @At("HEAD"))
    private BlitRenderState modifyBlitState(BlitRenderState original) {
        return new BlitRenderState(
            original.pipeline(), original.textureSetup(), HudManager.CURRENT.apply(original.pose()),
            original.x0(), original.y0(), original.x1(), original.y1(), original.u0(), original.u1(), original.v0(), original.v1(),
            ARGB.srgbLerp(HudManager.CURRENT.Opacity, 0, original.color()), original.scissorArea(), original.bounds()
        );
    }

    @ModifyVariable(method = "addText", ordinal = 0, argsOnly = true, at = @At("HEAD"))
    private GuiTextRenderState modifyTextState(GuiTextRenderState original) {
        return new GuiTextRenderState(
            original.font, original.text, HudManager.CURRENT.apply(original.pose),
            original.x, original.y, ARGB.multiplyAlpha(original.color, HudManager.CURRENT.Opacity),
            ARGB.multiplyAlpha(original.backgroundColor, HudManager.CURRENT.Opacity), original.dropShadow, false, original.scissor
        );
    }

    @ModifyVariable(method = "addGuiElement", ordinal = 0, argsOnly = true, at = @At("HEAD"))
    private GuiElementRenderState modifyElementState(GuiElementRenderState original) {
        return switch (original) {
            case ColoredRectangleRenderState output -> new ColoredRectangleRenderState(
                output.pipeline(), output.textureSetup(), HudManager.CURRENT.apply(output.pose()),
                output.x0(), output.y0(), output.x1(), output.y1(), ARGB.multiplyAlpha(output.col1(), HudManager.CURRENT.Opacity),
                ARGB.multiplyAlpha(output.col2(), HudManager.CURRENT.Opacity), original.scissorArea(), original.bounds()
            );
            case BlitRenderState output -> new BlitRenderState(
                output.pipeline(), output.textureSetup(), HudManager.CURRENT.apply(output.pose()),
                output.x0(), output.y0(), output.x1(), output.y1(), output.u0(), output.u1(), output.v0(), output.v1(),
                ARGB.multiplyAlpha(output.color(), HudManager.CURRENT.Opacity), original.scissorArea(), original.bounds()
            );
            case TiledBlitRenderState output -> new TiledBlitRenderState(
                output.pipeline(), output.textureSetup(), HudManager.CURRENT.apply(output.pose()), output.tileWidth(), output.tileHeight(),
                output.x0(), output.y0(), output.x1(), output.y1(), output.u0(), output.u1(), output.v0(), output.v1(),
                ARGB.multiplyAlpha(output.color(), HudManager.CURRENT.Opacity), original.scissorArea(), original.bounds()
            );
            default -> original;
        };
    }

    @ModifyVariable(method = "addItem", ordinal = 0, argsOnly = true, at = @At("HEAD"))
    private GuiItemRenderState storeItemState(GuiItemRenderState original) {
        if (HudManager.CURRENT != HudManager.DEFAULT)
            HudManager.CONTENT.put(original, HudManager.CURRENT);
        return original;
    }

    @ModifyVariable(method = "addPicturesInPictureState", ordinal = 0, argsOnly = true, at = @At("HEAD"))
    private PictureInPictureRenderState storeExtraState(PictureInPictureRenderState original) {
        if (HudManager.CURRENT != HudManager.DEFAULT)
            HudManager.CONTENT.put(original, HudManager.CURRENT);
        return original;
    }
}