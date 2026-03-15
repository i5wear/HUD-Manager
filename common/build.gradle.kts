plugins {
    alias(libs.plugins.fabric.loom)
}

repositories {
    maven("https://api.modrinth.com/maven/")
    maven("https://cursemaven.com/")
}

dependencies {
    minecraft(libs.minecraft)
    compileOnly(libs.fabric.loader)
}

loom {
    mods.clear()
    runs.clear()
}