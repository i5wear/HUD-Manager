plugins {
    alias(libs.plugins.neoforge.dev)
}

repositories {
    maven { url = uri("https://api.modrinth.com/maven/") }
    maven { url = uri("https://cursemaven.com/") }
}

dependencies {
    neoForge.version = libs.versions.neoforge.base.get()
    implementation(project(":common"))
}

tasks.withType<ProcessResources> {
    expand(mapOf("version" to rootProject.version))
}

neoForge.mods.maybeCreate("main").apply {
    sourceSet(project.sourceSets.main.get())
    sourceSet(project(":common").sourceSets.main.get())
}

neoForge.runs.maybeCreate("client").apply {
    client()
    ideName = "NeoForge Client"
    gameDirectory = project.file("run/client")
}

neoForge.runs.maybeCreate("server").apply {
    server()
    ideName = "NeoForge Server"
    gameDirectory = project.file("run/server")
}