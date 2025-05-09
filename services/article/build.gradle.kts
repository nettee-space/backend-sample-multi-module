val articleApi: String by project
val articleApplication: String by project
val articleWebMvcAdapter: String by project
val articleRdbAdapter: String by project

dependencies {
    api(project(articleApi))
    api(project(articleApplication))
    api(project(articleWebMvcAdapter))
    api(project(articleRdbAdapter))
}