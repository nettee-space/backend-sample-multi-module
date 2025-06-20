val commentDomain: String by project
val commentException: String by project
val commentReadModel: String by project

dependencies {
    api(project(commentDomain))
    api(project(commentException))
    api(project(commentReadModel))
}