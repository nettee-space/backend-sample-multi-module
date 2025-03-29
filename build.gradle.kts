import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
    id("java-library")
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management")  version "1.1.7"
}

allprojects {
    group = "me.nettee"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply {
        plugin("java")
        plugin("java-library")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    dependencies {
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
    }

    repositories {
        mavenCentral()
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