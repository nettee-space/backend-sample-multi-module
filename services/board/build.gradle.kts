val boardApi: String by project
val boardApplication: String by project
val boardRdbAdapter: String by project
val boardWebMvcAdapter: String by project
val boardRestClient: String by project

dependencies {
    api(project(boardApi))
    api(project(boardApplication))
    api(project(boardRdbAdapter))
    api(project(boardWebMvcAdapter))
    api(project(boardRestClient))
}