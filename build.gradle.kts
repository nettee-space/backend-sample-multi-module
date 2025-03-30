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

    dependencies {
        if(project.name != "common") {
            api(project(":common"))
        }

        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testCompileOnly("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok")

        compileOnly("org.springframework:spring-web")
        compileOnly("org.springframework:spring-context")
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
}