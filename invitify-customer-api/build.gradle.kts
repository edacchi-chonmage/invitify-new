plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.gradle.jooq)
}

group = "com.invitify.customer.api"
version = "1.0-SNAPSHOT"
val javaVersion = JavaVersion.toVersion(libs.versions.java.get())
java.sourceCompatibility = javaVersion
java.targetCompatibility = javaVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jooq)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.jooq)
    runtimeOnly(libs.mysql.driver)
    jooqGenerator(libs.mysql.driver)
    testImplementation(libs.spring.boot.starter.test) {
        exclude(module = "junit")
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}

jooq {
    version.set(libs.versions.jooq.get())
    edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)
    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)
            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "com.mysql.cj.jdbc.Driver"
                    url = "jdbc:mysql://localhost:3306/invitify-database"
                    user = "root"
                    password = "root"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        includes = ".*"
                        excludes = "flyway_schema_history"
                    }
                    generate.apply {
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "com.invitify.database.generated"
                        directory = "src/main/java"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}