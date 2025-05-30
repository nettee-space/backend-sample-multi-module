dependencies{
    api(project(":redis-api"))
    compileOnly("org.springframework:spring-context")
    compileOnly("org.springframework.boot:spring-boot-starter-data-redis")
}