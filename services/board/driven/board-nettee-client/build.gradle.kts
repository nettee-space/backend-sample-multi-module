val boardApi: String by project
val boardApplication: String by project

dependencies {
    api(project(boardApi))
    api(project(boardApplication))
    api(project(":rest-client"))
}