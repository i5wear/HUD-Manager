package com.github.i5wear.hudmanager.fabric;

import com.github.i5wear.hudmanager.screen.ModOptionsScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenu implements ModMenuApi {

    @Override public ConfigScreenFactory<?> getModConfigScreenFactory() { return ModOptionsScreen::new; }

}