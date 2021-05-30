plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "de.hglabor.plugins"
version = "0.0.1"

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    maven("https://repo.velocitypowered.com/releases/")
    maven("https://libraries.minecraft.net/")
    maven("https://repo.spongepowered.org/maven")
}

tasks {
    shadowJar {
        minimize()
    }
}

dependencies {
    compile("de.hglabor:localization:0.0.7")
    compile("de.hglabor:hglabor-utils:0.0.11")
    compileOnly("com.velocitypowered:velocity-api:1.1.4")
    annotationProcessor("com.velocitypowered:velocity-api:1.1.4")
}
