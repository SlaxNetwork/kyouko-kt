val ktor_version: String by project

plugins {
    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"

    id("com.github.johnrengelman.shadow") version "7.1.2"
    `maven-publish`
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")

    api("io.ktor:ktor-client-core:$ktor_version")
    api("io.ktor:ktor-client-cio:$ktor_version")
    api("io.ktor:ktor-client-content-negotiation:$ktor_version")
    api("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    api(project(":shared"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

publishing {
    publications {
        create<MavenPublication>(project.name.toLowerCase()) {
            groupId = "io.github.slaxnetwork"
            artifactId = "kyouko-api"
            version = "${project.version}"

            from(components["java"])
        }
    }
}