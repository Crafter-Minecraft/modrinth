plugins {
    application
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization").version("2.0.20")
}

group = "com.crafter"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
}

dependencies {
    implementation(libs.bundles.ktor.client)
}

kotlin {
    jvmToolchain(21)
    explicitApi()
}

application {
    mainClass.set("com.crafter.Modrinth")
}