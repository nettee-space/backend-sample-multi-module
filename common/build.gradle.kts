// FIXME remove this after root build.gradle.kts supplies below dependencies.
dependencies {
    testImplementation("org.springframework:spring-web:6.2.3")

    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.mockk:mockk:1.13.12")
    testImplementation(kotlin("script-runtime"))
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
}

// FIXME remove this after root build.gradle.kts supplies below task.
kotlin {
    sourceSets {
        test {
            kotlin.srcDirs(listOf("src/test/kotlin"))
        }
    }
}