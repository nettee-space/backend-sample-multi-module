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

val post = getDirectories("services", "post")

include (
        ":post",
        ":post:api",
        ":post:api:domain",
        ":post:api:exception",
        ":post:api:readmodel",
        ":post:application",
        ":post:rdb-adapter",
        ":post:webmvc-adapter",
)

project(":post").projectDir = post("post")
project(":post:api").projectDir = post("api")
project(":post:api:domain").projectDir = post("domain")
project(":post:api:exception").projectDir = post("exception")
project(":post:api:readmodel").projectDir = post("readmodel")
project(":post:application").projectDir = post("application")
project(":post:rdb-adapter").projectDir = post("rdb")
project(":post:webmvc-adapter").projectDir = post("web-mvc")
