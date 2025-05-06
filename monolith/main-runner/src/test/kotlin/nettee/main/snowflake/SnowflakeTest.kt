package nettee.main.snowflake

import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.*
import nettee.main.sample.entity.Sample
import nettee.main.sample.persistence.SampleRepository
import nettee.snowflake.properties.SnowflakeProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@DataJpaTest
class SnowflakeTest(
    @Autowired val repository: SampleRepository
) : FreeSpec({

    "[POST] Snowflake ID 채번 정책" - {
        "Snowflake ID가 생성이 됐을 때" - {
            val sample = repository.save(Sample())

            "Snowflake ID 정상적으로 생성된다" {
                sample.id shouldNotBe null
            }

            "Snowflake ID가 양수이다" {
                sample.id shouldBeGreaterThan 0L
            }

            "Snowflake ID의 크기는 부호 비트를 제외한 63bit 이내를 충족한다" {
                // 63번째 비트가 꺼져 있는지(0) 확인하여, Snowflake ID가 63비트 이내(양수 범위)임을 보장한다.
                // 0 and 1  => 0 이고 1 and 1 -> 1
                sample.id and (1L.shl(63)) shouldBe 0L
            }
        }
    }

    "[POST] Snowflake ID 동시성 테스트" - {
        "[병렬 처리] 100개의 Snowflake ID의 동시 생성 요청" - {
            // Set을 이용하여 키를 저장하고 중복없는 지를 판단
            val concurrentSet = ConcurrentHashMap.newKeySet<Long>()

            // 코루틴 실행
            val coroutineScope = CoroutineScope(Dispatchers.Default)

            val jobs = List(100) {
                // 병렬 작업 생성
                coroutineScope.launch {
                    // 새로운 sample 엔티티 저장 요청
                    val sample = repository.save(Sample())
                    println("Saved id=${sample.id}, time=${System.currentTimeMillis()}")
                    concurrentSet.add(sample.id)
                }
            }

            jobs.joinAll()

            "ID 중복이 없어야 한다" {
                concurrentSet shouldHaveSize  100
            }
        }
    }
}) {
    override fun extensions() = listOf(SpringExtension)

    @TestConfiguration
    class SnowflakeTestConfig {

        @Bean
        fun snowflakeProperties(): SnowflakeProperties {
            return SnowflakeProperties(1L, 1L, null)
        }
    }
}