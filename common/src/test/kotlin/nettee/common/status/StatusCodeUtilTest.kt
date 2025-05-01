package nettee.common.status

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import nettee.common.status.StatusParameters.GeneralPurposeFeatures.*

class StatusCodeUtilTest: FreeSpec({
    "[GP] 다른 섹션이 모두 0일 때" - {
        val cases = listOf(
            // Single GP
            setOf(READ) to 0x40_00_00_00,
            setOf(UPDATE) to 0x20_00_00_00,
            setOf(SUBITEM_READ) to 0x08_00_00_00,
            setOf(SUBITEM_UPDATE) to 0x04_00_00_00,

            // 2 GP items
            setOf(READ, UPDATE) to 0x60_00_00_00,
            setOf(READ, SUBITEM_READ) to 0x48_00_00_00,
            setOf(READ, SUBITEM_UPDATE) to 0x44_00_00_00,
            setOf(UPDATE, SUBITEM_READ) to 0x28_00_00_00,
            setOf(UPDATE, SUBITEM_UPDATE) to 0x24_00_00_00,
            setOf(SUBITEM_READ, SUBITEM_UPDATE) to 0x0C_00_00_00,

            // 3 GP items
            setOf(READ, UPDATE, SUBITEM_READ) to 0x68_00_00_00,
            setOf(READ, UPDATE, SUBITEM_UPDATE) to 0x64_00_00_00,
            setOf(READ, SUBITEM_READ, SUBITEM_UPDATE) to 0x4C_00_00_00,
            setOf(UPDATE, SUBITEM_READ, SUBITEM_UPDATE) to 0x2C_00_00_00,
        )

        cases.forEach { (features, expectedCode) ->
            "GP: ${features.joinToString()}" {
                val parameters = StatusParameters.generate()
                    .generalPurposeFeatures(*features.toTypedArray())
                    .systemInfoBits(0)
                    .categoryBits(0)
                    .instanceBits(0)

                val code = StatusCodeUtil.getAsInt(parameters)

                code shouldBe expectedCode
            }
        }
    }
})