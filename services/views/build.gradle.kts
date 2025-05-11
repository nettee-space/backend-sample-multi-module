val viewsApi: String by project
val viewsApplication: String by project
val viewsRedisAdapter: String by project
val viewsWebAdapter: String by project

dependencies {
    api(project(viewsApi))
    api(project(viewsApplication))
    api(project(viewsRedisAdapter))
    api(project(viewsWebAdapter))
}