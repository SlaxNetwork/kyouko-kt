val ktor_version: String by project

plugins {
    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
}
group = "io.github.slaxnetwork"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")

    compileOnly("com.github.jasync-sql:jasync-postgresql:2.1.8")

    api("io.ktor:ktor-client-core:$ktor_version")
    api("io.ktor:ktor-client-cio:$ktor_version")
    api("io.ktor:ktor-client-content-negotiation:$ktor_version")
    api("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation(project(":shared"))

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}