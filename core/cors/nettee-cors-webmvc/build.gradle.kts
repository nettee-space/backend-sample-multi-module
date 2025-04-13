dependencies {
    api(project(":cors-api"))
    compileOnly("org.springframework:spring-webmvc:6.2.2")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}