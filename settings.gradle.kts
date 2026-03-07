pluginManagement {
    repositories.maven { url = uri("https://maven.fabricmc.net/") }
    repositories.maven { url = uri("https://maven.neoforged.net/releases/") }
    repositories.gradlePluginPortal()
}

rootProject.name = "hudmanager"
include("common", "fabric", "neoforge")