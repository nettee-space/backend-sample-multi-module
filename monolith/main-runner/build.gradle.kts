import org.springframework.boot.gradle.tasks.bundling.BootJar

version = "0.0.1-SNAPSHOT"

dependencies {
    // core
    implementation(project(":exception-handler-core"))
    implementation(project(":jpa-core"))
    implementation(project(":cors-webmvc"))
    // service
    implementation(project(":board"))
    // webmvc
    implementation("org.springframework.boot:spring-boot-starter-web")
    // db
    runtimeOnly("org.postgresql:postgresql:42.7.4")
    // flyway
    implementation("org.flywaydb:flyway-database-postgresql")
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