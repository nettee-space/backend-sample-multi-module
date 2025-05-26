val viewsApi: String by project
val viewsApplication: String by project
val viewsRedisAdapter: String by project
val viewsRdbAdapter: String by project
val viewsWebMvcAdapter: String by project

dependencies {
    api(project(viewsApi))
    api(project(viewsApplication))
    api(project(viewsRedisAdapter))
    api(project(viewsRdbAdapter))
    api(project(viewsWebMvcAdapter))
}