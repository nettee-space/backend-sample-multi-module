val commentDomain: String by project

dependencies {
    api(project(commentDomain))
}