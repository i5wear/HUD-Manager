plugins {
    `java-library`
}

java {
    withSourcesJar()
    project.version = libs.versions.project.get()
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks.named<Jar>("jar") {
    from(project.file("LICENSE"))
    subprojects.forEach { from(it.sourceSets.main.get().output) }
    filesMatching("**/*.json") { expand(mapOf("version" to project.version)) }
    filesMatching("**/*.toml") { expand(mapOf("version" to project.version)) }
}

tasks.named<Jar>("sourcesJar") {
    from(project.file("LICENSE"))
    subprojects.forEach { from(it.sourceSets.main.get().allSource) }
    filesMatching("**/*.json") { expand(mapOf("version" to project.version)) }
    filesMatching("**/*.toml") { expand(mapOf("version" to project.version)) }
}