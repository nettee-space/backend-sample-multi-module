val articleApi: String by project

dependencies {
    api(project(articleApi))
    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}