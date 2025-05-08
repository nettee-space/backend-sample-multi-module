val articleDomain: String by project

dependencies {
    api(project(articleDomain))
}