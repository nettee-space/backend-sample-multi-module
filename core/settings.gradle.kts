val SUFFIX = "${extra["CORE_MODULE_SUFFIX"]}"

include(
        "${SUFFIX}",
        "${SUFFIX}:exception-handler",
        "${SUFFIX}:jpa"
)