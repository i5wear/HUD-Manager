plugins {
    alias(libs.plugins.fabric.loom)
}

repositories {
    maven { url = uri("https://api.modrinth.com/maven/") }
    maven { url = uri("https://cursemaven.com/") }
}

dependencies {
    minecraft(libs.minecraft)
    compileOnly(libs.fabric.loader)
}

loom {
    mods.clear()
    runs.clear()
}