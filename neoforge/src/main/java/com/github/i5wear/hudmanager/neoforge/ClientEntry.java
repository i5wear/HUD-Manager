package com.github.i5wear.hudmanager.neoforge;

import com.github.i5wear.hudmanager.ModOptions;
import com.github.i5wear.hudmanager.HudManager;
import com.github.i5wear.hudmanager.ModOptionsScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.Map;
import java.util.stream.Stream;

@Mod(value = "hudmanager", dist = Dist.CLIENT)
public final class ClientEntry {

    private static void modifyElement(RegisterGuiLayersEvent event) {
        Stream.of(
            Map.entry(VanillaGuiLayers.CROSSHAIR, ModOptions.INSTANCE.Crosshair),
            Map.entry(VanillaGuiLayers.HOTBAR, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.PLAYER_HEALTH, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.ARMOR_LEVEL, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.FOOD_LEVEL, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.VEHICLE_HEALTH, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.AIR_LEVEL, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.CONTEXTUAL_INFO_BAR_BACKGROUND, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.EXPERIENCE_LEVEL, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.CONTEXTUAL_INFO_BAR, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.SELECTED_ITEM_NAME, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.SPECTATOR_TOOLTIP, ModOptions.INSTANCE.HotbarGroup),
            Map.entry(VanillaGuiLayers.EFFECTS, ModOptions.INSTANCE.StatusEffect),
            Map.entry(VanillaGuiLayers.SCOREBOARD_SIDEBAR, ModOptions.INSTANCE.Scoreboard),
            Map.entry(VanillaGuiLayers.OVERLAY_MESSAGE, ModOptions.INSTANCE.ActionBar),
            Map.entry(VanillaGuiLayers.TITLE, ModOptions.INSTANCE.ScreenTitle),
            Map.entry(VanillaGuiLayers.TAB_LIST, ModOptions.INSTANCE.PlayerList)
        ).forEach(
            entry -> event.wrapLayer(
                entry.getKey(), original -> (graphics, ignore) -> {
                    if (entry.getValue().apply(graphics.pose()))
                        original.render(graphics, ignore);
                    HudManager.reset(graphics.pose());
                }
            )
        );
    }

    public ClientEntry(ModContainer loader) {
        loader.getEventBus().addListener(ClientEntry::modifyElement);
        loader.registerExtensionPoint(IConfigScreenFactory.class, ModOptionsScreen::new);
        ModOptions.DIRECTORY = FMLPaths.CONFIGDIR.get();
        ModOptions.load(); ModOptions.save();
    }
}