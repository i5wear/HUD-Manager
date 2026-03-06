plugins {
    alias(libs.plugins.fabric.loom)
}

dependencies {
    minecraft(libs.minecraft)
    implementation(libs.bundles.fabric.base)
    implementation(project(":common"))
}

tasks.withType<ProcessResources>().configureEach {
    expand(mapOf("version" to rootProject.version))
}

loom.mods.maybeCreate("main").apply {
    sourceSet(project.sourceSets.main.get())
    sourceSet(project(":common").sourceSets.main.get())
}

loom.runs.maybeCreate("client").apply {
    ideConfigGenerated(true)
    configName = "Fabric Client"
    runDir = "run/client"
    client()
}

loom.runs.maybeCreate("server").apply {
    ideConfigGenerated(true)
    configName = "Fabric Server"
    runDir = "run/server"
    server()
}