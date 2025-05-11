val boardDomain: String by project
val boardException: String by project
val boardReadModel: String by project

dependencies {
    api(project(boardDomain))
    api(project(boardException))
    api(project(boardReadModel))
}