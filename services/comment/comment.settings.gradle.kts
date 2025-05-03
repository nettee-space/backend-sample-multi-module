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

val comment = getDirectories("services", "comment")

// SERVICE/COMMENT
include(
    ":comment",
    ":comment:api",
    ":comment:api:domain",
    ":comment:api:exception",
    ":comment:api:readmodel",
    ":comment:application",
    ":comment:rdb-adapter",
    ":comment:webmvc-adapter",
)

project(":comment").projectDir = comment("comment")
project(":comment:api").projectDir = comment("api")
project(":comment:api:domain").projectDir = comment("domain")
project(":comment:api:exception").projectDir = comment("exception")
project(":comment:api:readmodel").projectDir = comment("readmodel")
project(":comment:application").projectDir = comment("application")
project(":comment:rdb-adapter").projectDir = comment("rdb")
project(":comment:webmvc-adapter").projectDir = comment("web-mvc")
