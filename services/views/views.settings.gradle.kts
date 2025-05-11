val views: String by settings
val viewsApi: String by settings
val viewsDomain: String by settings
val viewsApplication: String by settings
val viewsRedisAdapter: String by settings
val viewsWebAdapter: String by settings

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

val viewsDirectory = getDirectories("services", "views")

// SERVICE/BOARD
include(
    views,
    viewsApi,
    viewsDomain,
    viewsApplication,
    viewsRedisAdapter,
    viewsWebAdapter,
)

project(views).projectDir = viewsDirectory("views")
project(viewsApi).projectDir = viewsDirectory("api")
project(viewsDomain).projectDir = viewsDirectory("domain")
project(viewsApplication).projectDir = viewsDirectory("application")
project(viewsRedisAdapter).projectDir = viewsDirectory("redis")
project(viewsWebAdapter).projectDir = viewsDirectory("web")
