val articleApplication: String by project
val articleDomain: String by project
val articleException: String by project
val articleReadModel: String by project

plugins {
    id("java-library")
}

dependencies {
    api(project(articleDomain))
    api(project(articleException))
    api(project(articleReadModel))
    api(project(articleApplication))

    // validation
    compileOnly("jakarta.validation:jakarta.validation-api")
    compileOnly("jakarta.annotation:jakarta.annotation-api")

    // mapstruct
    compileOnly("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // jackson
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")
}