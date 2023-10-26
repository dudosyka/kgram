import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String = "2.3.2"
val kotlinVersion: String = "1.9.10"
val kodeinVersion: String = "7.17.0"


plugins {
    kotlin("jvm") version "1.9.10"
    application
    kotlin("plugin.serialization") version "1.9.10"
}

group = "com.kgram"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    fun ktor(module: String = "", version: String = ktorVersion) = "io.ktor:ktor-$module:$version"
    fun jet(module: String = "", version: String = kotlinVersion) = "org.jetbrains.kotlin:$module:$version"

    implementation(ktor("client-core"))
    implementation(ktor("client-apache"))
    implementation(ktor("client-auth"))
    implementation(ktor("serialization-kotlinx-json"))
    implementation(ktor("client-content-negotiation"))

    implementation("org.kodein.di:kodein-di-jvm:$kodeinVersion")

    runtimeOnly(jet("kotlin-reflect"))
    implementation(jet("kotlin-reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "19"
}

application {
    mainClass.set("com.kgram.MainKt")
}