plugins {
    `java-library`
}

project.version = libs.versions.project.get()

tasks.withType<Jar>().configureEach {
    from(project.file("LICENSE"))
    from(project(":common").sourceSets.main.get().output)
    from(project(":fabric").sourceSets.main.get().output)
    from(project(":neoforge").sourceSets.main.get().output)
}

subprojects {
    repositories {
        maven { url = uri("https://maven.terraformersmc.com/releases/") }
        maven { url = uri("https://api.modrinth.com/maven/") }
        maven { url = uri("https://cursemaven.com/") }
        maven { url = uri("https://jitpack.io/") }
    }
}