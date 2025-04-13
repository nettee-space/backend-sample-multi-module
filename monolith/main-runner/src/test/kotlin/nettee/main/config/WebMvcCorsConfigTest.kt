package nettee.main.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import nettee.config.WebMvcCorsConfig
import nettee.properties.CorsProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(WebMvcCorsConfig::class)
@Import(WebMvcCorsConfigTest.CorsPropertiesTest::class)
class WebMvcCorsConfigTest(
    @Autowired private val mvc: MockMvc
) : FreeSpec({

    "[CORS 성공 요청] CORS 설정" - {
        "허용된 Origin으로 GET 요청을 할 때"- {
            val result = mvc.perform(
                options("/a")
                    .header("Origin", "http://localhost:3000")
                    .header("Access-Control-Request-Method", "GET")
                    .header("Access-Control-Request-Headers", "Content-Type")
            )

            "2XX 응답 상태 반환" {
                result.andExpect(status().is2xxSuccessful)
            }

            "HTTP 메소드 GET 허용" {
                result.andExpect(header().string("Access-Control-Allow-Methods", "GET"))
            }

            "Access-Control-Allow-Origin 헤더 반환" {
                result.andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
            }

            "Access-Control-Allow-Headers 에 Content-Type 포함" {
                result.andExpect(header().string("Access-Control-Allow-Headers", "Content-Type"))
            }

            "Access-Control-Expose-Headers 반환 확인" {
                result.andExpect(header().string("Access-Control-Expose-Headers", "*"))
            }
        }
    }

    "[CORS 실패 요청] CORS 설정" - {
        "허용되지 않은 Origin으로 GET 요청을 할 때"- {
            val result = mvc.perform(
                options("/a")
                    .header("Origin", "http://localhost:9999")
                    .header("Access-Control-Request-Method", "GET")
                    .header("Access-Control-Request-Headers", "Content-Type")
            )

            "4XX 응답 상태 반환" {
                result.andExpect(status().is4xxClientError)
            }
            
            "CORS 허용 헤더 미존재" {
                result
                    .andExpect(header().doesNotExist("Access-Control-Allow-Origin"))
                    .andExpect(header().doesNotExist("Access-Control-Allow-Methods"))
                    .andExpect(header().doesNotExist("Access-Control-Allow-Headers"))
            }
        }
    }
}) {
    override fun extensions() = listOf(SpringExtension)

    @TestConfiguration
    class CorsPropertiesTest {

        @Bean
        fun corsProperties(): CorsProperties {
            val corsWebMvcYmlJson = """
            {
              "endpoints": [
                {
                  "path": "/**",
                  "allowed": {
                    "headers": ["*"],
                    "methods": ["*"],
                    "origins": [
                      "http://localhost:3000",
                      "https://localhost:3000"
                    ],
                    "credentials": true
                  },
                  "exposed": {
                    "headers": ["*"]
                  },
                  "maxAge": 3600
                },
                {
                  "path": "/a",
                  "allowed": {
                    "headers": ["*"],
                    "methods": ["*"],
                    "origins": [
                      "http://localhost:3000",
                      "https://localhost:3000"
                    ],
                    "credentials": true
                  },
                  "exposed": {
                    "headers": ["*"]
                  },
                  "maxAge": 3600
                }
              ]
            }
            """.trimIndent()

            val objectMapper = ObjectMapper()
                .registerKotlinModule()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

            return objectMapper.readValue(corsWebMvcYmlJson, CorsProperties::class.java)
        }
    }
}
