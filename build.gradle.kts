plugins {
    `java-library`
}

subprojects {
    repositories {
        maven { url = uri("https://maven.terraformersmc.com/releases") }
    }
}

project.version = libs.versions.project.get()

tasks.withType<Jar>().configureEach {
    from(project.file("LICENSE"))
    from(project(":common").sourceSets.main.get().output)
    from(project(":fabric").sourceSets.main.get().output)
    from(project(":neoforge").sourceSets.main.get().output)
}