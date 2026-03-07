plugins {
    alias(libs.plugins.fabric.loom)
}

dependencies {
    minecraft(libs.minecraft)
    compileOnly(libs.fabric.loader)
}

loom {
    mods.clear()
    runs.clear()
}