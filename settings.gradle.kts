pluginManagement.repositories {
    maven("https://plugins.gradle.org/m2/")
    maven("https://maven.fabricmc.net/")
    maven("https://maven.neoforged.net/releases/")
}

rootProject.name = "hudmanager"
include("common", "fabric", "neoforge")