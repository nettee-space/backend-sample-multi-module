package nettee.snowflake.properties

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import nettee.snowflake.constants.SnowflakeConstants.NETTEE_EPOCH

class SnowflakePropertiesTest : FreeSpec({
    "[초기화] 프로퍼티 입력 값이 null 일 경우" -{
        val snowflakeProperties = SnowflakeProperties(null, null, null)

        "datacenterId의 값은 0을 반환" {
            snowflakeProperties.datacenterId shouldBe 0
        }

        "workerId의 값은 0을 반환" {
            snowflakeProperties.workerId shouldBe 0
        }

        "epoch의 값은 NETTEE_EPOCH 반환" {
            snowflakeProperties.epoch.shouldBe(NETTEE_EPOCH)
        }
    }
})
