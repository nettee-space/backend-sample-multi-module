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

val article = getDirectories("services", "article")

include (
        ":article",
        ":article:article-api",
        ":article:article-api:article-domain",
        ":article:article-api:article-exception",
        ":article:article-api:article-readmodel",
        ":article:article-application",
        ":article:article-rdb-adapter",
        ":article:article-webmvc-adapter",
)

project(":article").projectDir = article("article")
project(":article:article-api").projectDir = article("api")
project(":article:article-api:article-domain").projectDir = article("domain")
project(":article:article-api:article-exception").projectDir = article("exception")
project(":article:article-api:article-readmodel").projectDir = article("readmodel")
project(":article:article-application").projectDir = article("application")
project(":article:article-rdb-adapter").projectDir = article("rdb")
project(":article:article-webmvc-adapter").projectDir = article("web-mvc")
