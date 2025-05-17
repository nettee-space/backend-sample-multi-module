package nettee.common.status

import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.*
import nettee.common.status.CustomStatusParametersSupplier.LongGeneralPurposeFeaturesValue
import nettee.common.status.CustomStatusParameters.LongGeneralPurposeFeatures.*
import nettee.common.status.exception.StatusCodeException
import nettee.common.status.exception.StatusCodeErrorCode.*

class StatusCodeUtilTest_CustomStatusParameters: FreeSpec({
    "[복합]" - {
        "GP: 중복 없음." {
            val featureValues = LongGeneralPurposeFeaturesValue(
                0b100_0000__0000_0000____0000_0000__0000_0000L,
                0b010_0000__0000_0000____0000_0000__0000_0000L,
                0b000_0000__0000_0000____1000_0000__0000_0000L,
                0b000_0000__0000_0000____0100_0000__0000_0000L,
                mapOf(
                    "example1" to 0b000_1000__0000_0000____0000_0000__0000_0000L,
                    "example2" to 0b000_0100__0000_0000____0000_0000__0000_0000L,
                    "example3" to 0b000_0010__0000_0000____0000_0000__0000_0000L,
                )
            )

            val supplier = CustomStatusParametersSupplier(
                31,
                16,
                8,
                8,
                featureValues
            ).get()

            val parameters = supplier
                .generalPurposeFeatures(
                    listOf("example1", "example2", "example3"),
                    READ,
                    UPDATE
                )
                .systemInfoBits(2)
                .categoryBits(1)
                .instanceBits(4)

            val code = StatusCodeUtil.getAsLong(parameters)

            code shouldBe 0x6E_00_00_00____0002____01____04L
        }
    }

    "[GP] GP 중복" - {
        "기본 GP 범위 중복 시" - {
            val read = 0b110_0000__0000_0000____0000_0000__0000_0000L
            val update = 0b010_0000__0000_0000____0000_0000__0000_0000L
            val subItemRead = 0b000_0000__0000_0000____1000_0000__0000_0000L
            val subItemUpdate = 0b000_0000__0000_0000____0100_0000__0000_0000L
            val featureValues = mapOf(
                "example1" to 0b000_1000__0000_0000____0000_0000__0000_0000L,
                "example2" to 0b000_0100__0000_0000____0000_0000__0000_0000L,
                "example3" to 0b000_0010__0000_0000____0000_0000__0000_0000L,
            )
            "StatusCodeException" {
                shouldThrow<StatusCodeException> {
                    LongGeneralPurposeFeaturesValue(
                        read,
                        update,
                        subItemRead,
                        subItemUpdate,
                        featureValues,
                    )
                }
            }
        }

        "GP: 커스텀 GP 범위 중복 시 StatusCodeException" - {
            val read = 0b110_0000__0000_0000____0000_0000__0000_0000L
            val update = 0b010_0000__0000_0000____0000_0000__0000_0000L
            val subItemRead = 0b000_0000__0000_0000____1000_0000__0000_0000L
            val subItemUpdate = 0b000_0000__0000_0000____0100_0000__0000_0000L
            val featureValues = mapOf(
                "example1" to 0b000_1000__0000_0000____0000_0000__0000_0000L,
                "example2" to 0b000_1100__0000_0000____0000_0000__0000_0000L,
                "example3" to 0b000_1110__0000_0000____0000_0000__0000_0000L,
            )

            "StatusCodeException" {
                val exception = shouldThrow<StatusCodeException> {
                    LongGeneralPurposeFeaturesValue(
                        read,
                        update,
                        subItemRead,
                        subItemUpdate,
                        featureValues,
                    )
                }

                exception.errorCode shouldBe GP_BITS_NOT_DISTINCT
            }
        }
    }
})