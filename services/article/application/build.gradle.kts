val articleDomain: String by project
val articleException: String by project
val articleReadModel: String by project

dependencies {
    api(project(articleDomain))
    api(project(articleException))
    api(project(articleReadModel))
    // spring
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework:spring-tx")
}