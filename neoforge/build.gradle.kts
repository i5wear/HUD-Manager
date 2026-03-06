plugins {
    alias(libs.plugins.neoforge.dev)
}

dependencies {
    neoForge.version = libs.versions.neoforge.base.get()
    implementation(project(":common"))
}

tasks.withType<ProcessResources>().configureEach {
    expand(mapOf("version" to rootProject.version))
}

neoForge.mods.maybeCreate("main").apply {
    sourceSet(project.sourceSets.main.get())
    sourceSet(project(":common").sourceSets.main.get())
}

neoForge.runs.maybeCreate("client").apply {
    ideName = "NeoForge Client"
    gameDirectory = project.file("run/client")
    client()
}

neoForge.runs.maybeCreate("server").apply {
    ideName = "NeoForge Server"
    gameDirectory = project.file("run/server")
    server()
}