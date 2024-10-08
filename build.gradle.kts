plugins {
    kotlin("jvm") version "2.0.20"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.invitify"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}