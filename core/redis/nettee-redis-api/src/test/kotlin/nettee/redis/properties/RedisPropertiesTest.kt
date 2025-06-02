package nettee.redis.properties

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@Import(RedisPropertiesTest.RedisPropertiesTest::class)
class RedisPropertiesTest(
    private val redisProperties: RedisProperties
) : FreeSpec({

    "[초기화 검증] Redis 설정 값을 반환" - {
        "application.yml 모든 변수를 설정했을 때" - {
            "useClusterMode는 정상 반환" {
                redisProperties.useClusterMode shouldBe true
            }

            "host 정상 반환" {
                redisProperties.host shouldBe "127.0.0.1"
            }

            "ports 정상 반환" {
                redisProperties.ports shouldBe listOf(7000, 7001, 7002)
            }

            "cache.domains.article 정상 반환" {
                val article = redisProperties.cache.domains["article"]!!
                article.ttl() shouldBe 300
                article.disableNull() shouldBe true
                article.prefix() shouldBe "article::"
            }

            "cache.domains.comments 정상 반환" {
                val comments = redisProperties.cache.domains["comments"]!!
                comments.ttl() shouldBe 60
                comments.disableNull() shouldBe false
                comments.prefix() shouldBe "comments::"
            }
        }

        "application.yml 일부 변수를 누락했을 때" - {
            val nullRedisProperties = RedisProperties(null, null, null, null)

            "useClusterMode는 기본값 false 반환" {
                nullRedisProperties.useClusterMode shouldBe false
            }

            "host는 기본값 127.0.0.1(localhost) 반환" {
                nullRedisProperties.host shouldBe "127.0.0.1"
            }

            "ports 기본값 [6379] 반환" {
                nullRedisProperties.ports shouldBe listOf(6379)
            }

            "cache 기본값 cache 반환" {
                val app = nullRedisProperties.cache.domains["app"]!!
                app.ttl() shouldBe 60
                app.disableNull() shouldBe true
                app.prefix() shouldBe ""
            }
        }
    }

    "[예외 검증] useClusterMode 예외 반환" - {
        "useClusterMode가 false(standalone 모드) 일때" - {
            "여러 port를 지정하면 에러 반환" {
                shouldThrow<RuntimeException> {
                    RedisProperties(false, null, listOf(7000, 7001, 7002), null)
                }
            }
        }
    }
}) {
    override fun extensions() = listOf(SpringExtension)

    @TestConfiguration
    class RedisPropertiesTest {

        @Bean
        fun redisProperties(): RedisProperties {
            val redisYmlJson = """
            {
              "useClusterMode": true,
              "host": "127.0.0.1",
              "ports": [7000, 7001, 7002],
              "cache": {
                "domains": {
                  "article": {
                    "ttl": 300,
                    "disableNull": true,
                    "prefix": "article::"
                  },
                  "comments": {
                    "ttl": 60,
                    "disableNull": false,
                    "prefix": "comments::"
                  }
                }
              }
            }
            """.trimIndent()

            val objectMapper = ObjectMapper().registerKotlinModule()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

            return objectMapper.readValue(redisYmlJson, RedisProperties::class.java)
        }
    }
}
