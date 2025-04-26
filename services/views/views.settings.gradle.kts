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

val board = getDirectories("services", "views")

// SERVICE/BOARD
include(
    ":views",
    ":views:api",
    ":views:api:domain",
    ":views:application",
)

project(":views").projectDir = board("views")
project(":views:api").projectDir = board("api")
project(":views:api:domain").projectDir = board("domain")
project(":views:application").projectDir = board("application")
