plugins {
    alias(libs.plugins.neoforge.dev)
}

repositories {
    maven("https://api.modrinth.com/maven/")
    maven("https://cursemaven.com/")
}

dependencies {
    neoForge.version = libs.neoforge.get().version
    implementation(project(":common"))
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