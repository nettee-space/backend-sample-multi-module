val viewsApi: String by project
val viewsApplication: String by project

dependencies {
    api(project(viewsApi))
    api(project(viewsApplication))

    implementation("org.springframework.boot:spring-boot-starter-web")

    // validation
    compileOnly("jakarta.validation:jakarta.validation-api")
    compileOnly("jakarta.annotation:jakarta.annotation-api")

    // mapstruct
    compileOnly("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
}