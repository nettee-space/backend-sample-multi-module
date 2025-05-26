val viewsApi: String by project
val viewsApplication: String by project

dependencies {

    api(project(viewsApi))
    api(project(viewsApplication))
    api(project(":jpa-core"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("commons-codec:commons-codec:1.16.0")
}