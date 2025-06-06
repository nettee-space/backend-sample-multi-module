val core = rootDir.resolve("core")
    .walkTopDown()
    .maxDepth(3)
    .filter(File::isDirectory)
    .associateBy(File::getName)


include(
    ":time-util",
    ":jpa-core",
    ":exception-handler-core",
    ":cors-api",
    ":cors-webmvc",
    ":snowflake-id-api",
    ":snowflake-id-hibernate",
    ":client-api",
    ":rest-client"
)

project(":time-util").projectDir = core["time-util"]!!
project(":jpa-core").projectDir = core["jpa-core"]!!
project(":exception-handler-core").projectDir = core["exception-handler-core"]!!
project(":cors-webmvc").projectDir = core["nettee-cors-webmvc"]!!
project(":cors-api").projectDir = core["nettee-cors-api"]!!
project(":snowflake-id-api").projectDir = core["nettee-snowflake-id-api"]!!
project(":snowflake-id-hibernate").projectDir = core["nettee-snowflake-id-hibernate"]!!
project(":client-api").projectDir = core["nettee-client-api"]!!
project(":rest-client").projectDir = core["nettee-rest-client"]!!