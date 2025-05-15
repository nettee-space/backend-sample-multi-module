val articleDomain: String by project
val articleException: String by project
val articleReadModel: String by project

dependencies {
    api(project(articleDomain))
    api(project(articleException))
    api(project(articleReadModel))
    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}