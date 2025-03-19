val PREFIX = "${extra["CORE_MODULE_PREFIX"]}"

include(
        "${PREFIX}",
        "${PREFIX}:exception-handler",
        "${PREFIX}:jpa"
)