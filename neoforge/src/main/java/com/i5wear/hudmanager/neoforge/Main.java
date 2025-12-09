package com.i5wear.hudmanager.neoforge;

import com.i5wear.hudmanager.HudConfig;
import com.i5wear.hudmanager.HudManager;
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
public class Main {

    private static final Map<ResourceLocation, HudManager> CATEGORY = Map.ofEntries(
        Map.entry(VanillaGuiLayers.CROSSHAIR, HudConfig.INSTANCE.Crosshair),
        Map.entry(VanillaGuiLayers.HOTBAR, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.PLAYER_HEALTH, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.ARMOR_LEVEL, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.FOOD_LEVEL, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.VEHICLE_HEALTH, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.AIR_LEVEL, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.CONTEXTUAL_INFO_BAR_BACKGROUND, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.EXPERIENCE_LEVEL, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.CONTEXTUAL_INFO_BAR, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.SELECTED_ITEM_NAME, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.SPECTATOR_TOOLTIP, HudConfig.INSTANCE.HotbarGroup),
        Map.entry(VanillaGuiLayers.EFFECTS, HudConfig.INSTANCE.StatusEffect),
        Map.entry(VanillaGuiLayers.SCOREBOARD_SIDEBAR, HudConfig.INSTANCE.Scoreboard),
        Map.entry(VanillaGuiLayers.OVERLAY_MESSAGE, HudConfig.INSTANCE.ActionBar),
        Map.entry(VanillaGuiLayers.TITLE, HudConfig.INSTANCE.ScreenTitle),
        Map.entry(VanillaGuiLayers.TAB_LIST, HudConfig.INSTANCE.PlayerList)
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

    public Main(ModContainer container, IEventBus event) {
        HudConfig.load(FMLPaths.CONFIGDIR.get().resolve("hudmanager.json").toFile());
        HudConfig.save(FMLPaths.CONFIGDIR.get().resolve("hudmanager.json").toFile());
        container.registerExtensionPoint(IConfigScreenFactory.class, (IConfigScreenFactory) null);
        event.addListener(Main::modifyElement);
    }
}