dependencies {
    api(project(":client-api"))
    // Spring Release on 2025.4.17
    compileOnly("org.springframework:spring-web:6.2.6")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
}