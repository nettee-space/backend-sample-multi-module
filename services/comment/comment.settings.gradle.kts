val comment: String by settings
val commentApi: String by settings
val commentDomain: String by settings
val commentException: String by settings
val commentReadModel: String by settings
val commentApplication: String by settings
val commentRdbAdapter: String by settings
val commentWebMvcAdapter: String by settings


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

val commentDirectory = getDirectories("services", "comment")

// SERVICE/COMMENT
include(
    comment,
    commentApi,
    commentDomain,
    commentException,
    commentReadModel,
    commentApplication,
    commentRdbAdapter,
    commentWebMvcAdapter,
)

project(comment).projectDir = commentDirectory("comment")
project(commentApi).projectDir = commentDirectory("api")
project(commentDomain).projectDir = commentDirectory("domain")
project(commentException).projectDir = commentDirectory("exception")
project(commentReadModel).projectDir = commentDirectory("readmodel")
project(commentApplication).projectDir = commentDirectory("application")
project(commentRdbAdapter).projectDir = commentDirectory("rdb")
project(commentWebMvcAdapter).projectDir = commentDirectory("web-mvc")
