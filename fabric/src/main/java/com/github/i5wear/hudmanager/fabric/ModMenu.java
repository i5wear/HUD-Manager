package com.github.i5wear.hudmanager.fabric;

import com.github.i5wear.hudmanager.ModOptionsScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public final class ModMenu implements ModMenuApi {

    @Override public ConfigScreenFactory<?> getModConfigScreenFactory() { return ModOptionsScreen::new; }

}