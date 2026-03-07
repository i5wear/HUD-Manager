pluginManagement {
    repositories.maven { url = uri("https://plugins.gradle.org/m2/") }
    repositories.maven { url = uri("https://maven.fabricmc.net/") }
    repositories.maven { url = uri("https://maven.neoforged.net/releases/") }
}

rootProject.name = "hudmanager"
include("common", "fabric", "neoforge")