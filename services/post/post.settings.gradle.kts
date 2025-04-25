var post = rootDir.resolve("services/post")
        .walkTopDown()
        .maxDepth(3)
        .filter(File::isDirectory)
        .associateBy(File::getName)

include (
        ":post",
        ":post:api",
        ":post:api:domain",
        ":post:application",
)

project(":post").projectDir = post["post"]
project(":post:api").projectDir = post["api"]
project(":post:api:domain").projectDir = post["domain"]
project(":post:application").projectDir = post["application"]