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
    ":board:board-api",
    ":board:board-api-domain",
    ":board:board-api-exception",
    ":board:board-api-readmodel",
    ":board:board-application",
    ":board:board-rdb-adapter",
    ":board:board-webmvc-adapter",
    ":board:board-board-nettee-client",
)

project(":board").projectDir = board("board")
project(":board:board-api").projectDir = board("api")
project(":board:board-api-domain").projectDir = board("domain")
project(":board:board-api-exception").projectDir = board("exception")
project(":board:board-api-readmodel").projectDir = board("readmodel")
project(":board:board-application").projectDir = board("application")
project(":board:board-rdb-adapter").projectDir = board("rdb")
project(":board:board-webmvc-adapter").projectDir = board("web-mvc")
project(":board:board-board-nettee-client").projectDir = board("board-nettee-client")
