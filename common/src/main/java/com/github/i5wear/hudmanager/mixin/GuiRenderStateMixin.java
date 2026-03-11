package com.github.i5wear.hudmanager.mixin;

import com.github.i5wear.hudmanager.HudManager;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.renderer.state.gui.*;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiRenderState.class)
public abstract class GuiRenderStateMixin {

    @WrapMethod(method = "addPicturesInPictureState")
    private void storeExtraElement(PictureInPictureRenderState input, Operation<Void> original) {
        if (HudManager.CURRENT != HudManager.DEFAULT)
            HudManager.CONTENT.put(input, HudManager.CURRENT);
        original.call(input);
    }

    @ModifyVariable(method = "addGuiElement", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private GuiElementRenderState modifyElementColor(GuiElementRenderState original) {
        return switch (original) {
            case ColoredRectangleRenderState output -> new ColoredRectangleRenderState(
                output.pipeline(), output.textureSetup(), output.pose(),
                output.x0(), output.y0(), output.x1(), output.y1(), ARGB.multiplyAlpha(output.col1(), HudManager.CURRENT.Opacity),
                ARGB.multiplyAlpha(output.col2(), HudManager.CURRENT.Opacity), original.scissorArea()
            );
            case BlitRenderState output -> new BlitRenderState(
                output.pipeline(), output.textureSetup(), output.pose(),
                output.x0(), output.y0(), output.x1(), output.y1(), output.u0(), output.u1(), output.v0(), output.v1(),
                ARGB.multiplyAlpha(output.color(), HudManager.CURRENT.Opacity), original.scissorArea()
            );
            case TiledBlitRenderState output -> new TiledBlitRenderState(
                output.pipeline(), output.textureSetup(), output.pose(), output.tileWidth(), output.tileHeight(),
                output.x0(), output.y0(), output.x1(), output.y1(), output.u0(), output.u1(), output.v0(), output.v1(),
                ARGB.multiplyAlpha(output.color(), HudManager.CURRENT.Opacity), original.scissorArea()
            );
            default -> original;
        };
    }
}