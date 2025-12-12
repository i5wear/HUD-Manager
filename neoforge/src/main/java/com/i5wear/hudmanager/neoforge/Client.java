package com.i5wear.hudmanager.neoforge;

import com.i5wear.hudmanager.HudOptions;
import com.i5wear.hudmanager.HudManager;
import com.i5wear.hudmanager.screen.HudOptionsScreen;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.Map;

@Mod(value = "hudmanager", dist = Dist.CLIENT)
public class Client {

    private static final Map<ResourceLocation, HudManager> CATEGORY = Map.ofEntries(
        Map.entry(VanillaGuiLayers.CROSSHAIR, HudOptions.INSTANCE.Crosshair),
        Map.entry(VanillaGuiLayers.HOTBAR, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.PLAYER_HEALTH, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.ARMOR_LEVEL, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.FOOD_LEVEL, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.VEHICLE_HEALTH, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.AIR_LEVEL, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.CONTEXTUAL_INFO_BAR_BACKGROUND, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.EXPERIENCE_LEVEL, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.CONTEXTUAL_INFO_BAR, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.SELECTED_ITEM_NAME, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.SPECTATOR_TOOLTIP, HudOptions.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.EFFECTS, HudOptions.INSTANCE.StatusEffect),
        Map.entry(VanillaGuiLayers.SCOREBOARD_SIDEBAR, HudOptions.INSTANCE.Scoreboard),
        Map.entry(VanillaGuiLayers.OVERLAY_MESSAGE, HudOptions.INSTANCE.ActionBar),
        Map.entry(VanillaGuiLayers.TITLE, HudOptions.INSTANCE.ScreenTitle),
        Map.entry(VanillaGuiLayers.TAB_LIST, HudOptions.INSTANCE.PlayerList)
    );

    private static void modifyElement(RegisterGuiLayersEvent event) {
        CATEGORY.forEach(
            (key, value) -> event.wrapLayer(
                key, original -> (graphics, tracker) -> {
                    if (value.apply(graphics))
                        original.render(graphics, tracker);
                    HudManager.reset(graphics);
                }
            )
        );
    }

    public Client(ModContainer container, IEventBus event) {
        container.registerExtensionPoint(IConfigScreenFactory.class, (ignore, screen) -> new HudOptionsScreen(screen));
        HudOptions.CONFIG = FMLPaths.CONFIGDIR.get().resolve("hudmanager.json").toFile();
        HudOptions.load(); event.addListener(Client::modifyElement);
    }
}