val commentApi: String by project
val commentApplication: String by project
val commentRdbAdapter: String by project
val commentWebMvcAdapter: String by project

dependencies {
    api(project(commentApi))
    api(project(commentApplication))
    api(project(commentRdbAdapter))
    api(project(commentWebMvcAdapter))
}