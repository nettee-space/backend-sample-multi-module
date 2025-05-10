dependencies {
    api(project(":client-api"))
    api("org.springframework:spring-web:6.2.3")
    compileOnly("com.fasterxml.jackson.core:jackson-databind")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
}