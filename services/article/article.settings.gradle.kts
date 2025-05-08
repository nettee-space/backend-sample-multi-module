val article: String by settings
val articleApi: String by settings
val articleDomain: String by settings
val articleException: String by settings
val articleReadModel: String by settings
val articleApplication: String by settings
val articleRdbAdapter: String by settings
val articleWebMvcAdapter: String by settings

fun getDirectories(vararg names: String): (String) -> File {
    var dir = rootDir
    for (name in names) {
        dir = dir.resolve(name)
    }
    return { targetName ->
        val directory = dir.walkTopDown().maxDepth(3)
                .filter(File::isDirectory)
                .associateBy { it.name }
        directory[targetName] ?: throw Error("그런 폴더가 없습니다: $targetName")
    }
}

val articleDirectory = getDirectories("services", "article")

include (
        article,
        articleApi,
        articleDomain,
        articleException,
        articleReadModel,
        articleApplication,
        articleRdbAdapter,
        articleWebMvcAdapter,
)

project(article).projectDir = articleDirectory("article")
project(articleApi).projectDir = articleDirectory("api")
project(articleDomain).projectDir = articleDirectory("domain")
project(articleException).projectDir = articleDirectory("exception")
project(articleReadModel).projectDir = articleDirectory("readmodel")
project(articleApplication).projectDir = articleDirectory("application")
project(articleRdbAdapter).projectDir = articleDirectory("rdb")
project(articleWebMvcAdapter).projectDir = articleDirectory("web-mvc")
