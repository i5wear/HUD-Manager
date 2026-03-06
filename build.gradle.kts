plugins {
    `java-library`
}

subprojects {
    repositories {
        maven { url = uri("https://maven.fabricmc.net/") }
        maven { url = uri("https://maven.neoforged.net/releases") }
        maven { url = uri("https://maven.terraformersmc.com/releases") }
        mavenCentral()
    }
}

project.version = libs.versions.project.get()

tasks.withType<Jar>().configureEach {
    from(project.file("LICENSE"))
    from(project(":common").sourceSets.main.get().output)
    from(project(":fabric").sourceSets.main.get().output)
    from(project(":neoforge").sourceSets.main.get().output)
}