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

val board = getDirectories("services", "board")

// SERVICE/BOARD
include(
    ":board",
    ":board:api",
    ":board:api:domain",
    ":board:api:exception",
    ":board:api:readmodel",
    ":board:application",
    ":board:rdb-adapter",
    ":board:webmvc-adapter",
)

project(":board").projectDir = board("board")
project(":board:api").projectDir = board("api")
project(":board:api:domain").projectDir = board("domain")
project(":board:api:exception").projectDir = board("exception")
project(":board:api:readmodel").projectDir = board("readmodel")
project(":board:application").projectDir = board("application")
project(":board:rdb-adapter").projectDir = board("rdb")
project(":board:webmvc-adapter").projectDir = board("web-mvc")
