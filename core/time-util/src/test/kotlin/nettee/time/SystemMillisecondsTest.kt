package nettee.time

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.longs.shouldBeGreaterThanOrEqual
import io.kotest.matchers.longs.shouldBeLessThanOrEqual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class SystemMillisecondsTest: FreeSpec({
    val milliseconds = SystemMilliseconds()

    "getAsLong" - {
        "Clock-Backward가 발생하지 않는다." {
            val t0 = System.currentTimeMillis()
            val result = milliseconds.asLong
            val t1 = System.currentTimeMillis()

            result shouldBeGreaterThanOrEqual t0
            result shouldBeLessThanOrEqual t1
        }

        "concurrent calls should all complete" {
            coroutineScope {
                val jobs = (1..10).map {
                    launch(Dispatchers.Default) { milliseconds.asLong }
                }
                jobs.joinAll() // 모두 완료될 때까지 대기
            }
        }
    }
})