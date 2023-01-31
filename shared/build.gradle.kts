val ktor_version: String by project

plugins {
    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"

    `maven-publish`
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    api("io.ktor:ktor-client-core:$ktor_version")
    api("io.ktor:ktor-client-cio:$ktor_version")
    api("io.ktor:ktor-client-content-negotiation:$ktor_version")
    api("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

publishing {
    publications {
        create<MavenPublication>(project.name.toLowerCase()) {
            groupId = "io.github.slaxnetwork"
            artifactId = "kyouko-shared"
            version = "${project.version}"

            from(components["java"])
        }
    }
}