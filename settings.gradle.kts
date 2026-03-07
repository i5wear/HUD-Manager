rootProject.name = "hudmanager"
include("common", "fabric", "neoforge")

pluginManagement.repositories {
    maven { url = uri("https://maven.fabricmc.net/") }
    maven { url = uri("https://maven.neoforged.net/releases/") }
    gradlePluginPortal()
}

//dependencyResolutionManagement.repositories {}