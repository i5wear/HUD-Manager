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
    from(project(":common").sourceSets.main.get().output)
    from(project(":fabric").sourceSets.main.get().output)
    from(project(":neoforge").sourceSets.main.get().output)
}

tasks.named<Jar>("sourcesJar") {
    from(project.file("LICENSE"))
    from(project(":common").sourceSets.main.get().allSource)
    from(project(":fabric").sourceSets.main.get().allSource)
    from(project(":neoforge").sourceSets.main.get().allSource)
}