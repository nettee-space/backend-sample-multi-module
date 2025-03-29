val core = rootDir.resolve("core")
    .walkTopDown()
    .maxDepth(3)
    .filter(File::isDirectory)
    .associateBy(File::getName)


include(
    ":jpa-core",
)

project(":jpa-core").projectDir = core["jpa-core"]!!