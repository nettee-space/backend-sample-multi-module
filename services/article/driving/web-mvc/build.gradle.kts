plugins {
    id("java-library")
}

dependencies {
    api(project(":article:article-api"))
    api(project(":article:article-application"))

    // validation
    compileOnly("jakarta.validation:jakarta.validation-api")
    compileOnly("jakarta.annotation:jakarta.annotation-api")

    // mapstruct
    compileOnly("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}