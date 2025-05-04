import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
    id("java-library")
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management")  version "1.1.7"
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.20"
}

allprojects {
    group = "me.nettee"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("java")
        plugin("java-library")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
        configureEach {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        }
    }

    dependencies {
        // FIXME determine the placement of logging library later
        implementation("org.springframework.boot:spring-boot-starter-log4j2")
        if(project.name != "common") {
            api(project(":common"))
        }

        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testCompileOnly("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok")

        compileOnly("org.springframework:spring-web")
        compileOnly("org.springframework:spring-context")

        testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
        testImplementation("io.mockk:mockk:1.13.12")
        testImplementation(kotlin("script-runtime"))
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    }

    tasks.withType<BootJar>{
        enabled = false
    }

    tasks.withType<Jar>{
        enabled = true
    }

    tasks.test {
        useJUnitPlatform()
    }

    kotlin{
        sourceSets {
            test {
                kotlin.srcDirs(listOf("src/test/kotlin"))
            }
        }
    }
}