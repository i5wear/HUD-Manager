package com.i5wear.hudmanager.fabric;

import com.i5wear.hudmanager.screen.HudOptionsScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenu implements ModMenuApi {

    @Override public ConfigScreenFactory<?> getModConfigScreenFactory() { return HudOptionsScreen::new; }

}