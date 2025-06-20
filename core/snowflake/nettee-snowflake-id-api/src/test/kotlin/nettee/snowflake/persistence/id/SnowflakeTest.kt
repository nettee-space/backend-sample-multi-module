package nettee.snowflake.persistence.id

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import nettee.snowflake.constants.SnowflakeConstants.NETTEE_EPOCH
import nettee.snowflake.time.TestMilliseconds

class SnowflakeTest : FreeSpec({
    "[snowflake ID 채번] 특정 밀리세컨드 시간이 주어질 때" - {
        val testMilliseconds = TestMilliseconds()
        val testSnowflake = Snowflake(0, 0, NETTEE_EPOCH, testMilliseconds)

        println("현재 밀리세컨드: ${testMilliseconds.currentMilliseconds}")

        "총 4096개의 키 생성" {
            val ids = (0..4095).map { testSnowflake.nextId() }

            ids.toSet().size shouldBe 4096
        }

        "이 후 특정 밀리세컨드가 증가하지 않는 상태에서 키 생성 시" - {
            val thread = Thread {
                testSnowflake.nextId()
            }

            thread.start()

            thread.join(500)

            "무한 루프 발생" {
                // 현재도 쓰레드가 살아 있다면 무한 루프
                thread.isAlive shouldBe true
                thread.interrupt()
            }

            "특정 밀리세컨드에서 증가 할 때 정상 키 생성" {
                testMilliseconds.nextMillisecond()

                println("다음 밀리세컨드: ${testMilliseconds.currentMilliseconds}")
                val id = testSnowflake.nextId()

                id shouldNotBe null
                thread.isAlive shouldBe false
            }
        }
    }
})