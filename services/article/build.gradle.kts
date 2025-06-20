val articleApplication: String by project
val articleWebMvcAdapter: String by project
val articleRdbAdapter: String by project

dependencies {
    api(project(articleApplication))
    api(project(articleWebMvcAdapter))
    api(project(articleRdbAdapter))
}