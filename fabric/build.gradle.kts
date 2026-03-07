plugins {
    alias(libs.plugins.fabric.loom)
}

repositories {
    maven { url = uri("https://maven.terraformersmc.com/releases/") }
    maven { url = uri("https://api.modrinth.com/maven/") }
    maven { url = uri("https://cursemaven.com/") }
}

dependencies {
    minecraft(libs.minecraft)
    implementation(libs.bundles.fabric.base)
    implementation(project(":common"))
}

tasks.withType<ProcessResources> {
    expand(mapOf("version" to rootProject.version))
}

loom.mods.maybeCreate("main").apply {
    sourceSet(project.sourceSets.main.get())
    sourceSet(project(":common").sourceSets.main.get())
}

loom.runs.maybeCreate("client").apply {
    client()
    ideConfigGenerated(true)
    configName = "Fabric Client"
    runDir = "run/client"
}

loom.runs.maybeCreate("server").apply {
    server()
    ideConfigGenerated(true)
    configName = "Fabric Server"
    runDir = "run/server"
}