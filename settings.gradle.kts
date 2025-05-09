rootProject.name = "backend-sample-multi-module"

val services = "${rootProject.projectDir}/services"

apply(from = "common/common.settings.gradle.kts")
apply(from = "core/core.settings.gradle.kts")
apply(from = "monolith/monolith.settings.gradle.kts")

apply(from = "$services/board/board.settings.gradle.kts")
apply(from = "$services/comment/comment.settings.gradle.kts")