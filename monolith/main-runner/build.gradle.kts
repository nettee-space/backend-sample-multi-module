import org.springframework.boot.gradle.tasks.bundling.BootJar

version = "0.0.1-SNAPSHOT"

dependencies {
    // core
    implementation(project(":exception-handler-core"))
    implementation(project(":jpa-core"))
    // service
    implementation(project(":board"))
    // webmvc
    implementation("org.springframework.boot:spring-boot-starter-web")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootJar>{
    enabled = true
}

tasks.withType<Jar>{
    enabled = false
}