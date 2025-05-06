plugins {
    id("java-library")
}

dependencies {
    api(project(":post-api"))
    api(project(":post-application"))

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