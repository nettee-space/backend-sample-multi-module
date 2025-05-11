val board: String by settings
val boardApi: String by settings
val boardDomain: String by settings
val boardException: String by settings
val boardReadModel: String by settings
val boardApplication: String by settings
val boardRdbAdapter: String by settings
val boardWebMvcAdapter: String by settings

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

val boardDirectory = getDirectories("services", "board")

// SERVICE/BOARD
include(
    board,
    boardApi,
    boardDomain,
    boardException,
    boardReadModel,
    boardApplication,
    boardRdbAdapter,
    boardWebMvcAdapter,
)

project(board).projectDir = boardDirectory("board")
project(boardApi).projectDir = boardDirectory("api")
project(boardDomain).projectDir = boardDirectory("domain")
project(boardException).projectDir = boardDirectory("exception")
project(boardReadModel).projectDir = boardDirectory("readmodel")
project(boardApplication).projectDir = boardDirectory("application")
project(boardRdbAdapter).projectDir = boardDirectory("rdb")
project(boardWebMvcAdapter).projectDir = boardDirectory("web-mvc")
