package nettee.board.web

import nettee.views.Views
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestClient
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


class ViewsApiTest {

    private val restClient: RestClient = RestClient.create("http://localhost:5000")

    @Test
    fun viewIncreaseTest() {
        val executorService = Executors.newFixedThreadPool(100)
        val countDownLatch = CountDownLatch(100)

        val views = Views.builder()
            .postId(1L)
            .userId(1L)
            .build()

        repeat(100) {
            executorService.submit {
                restClient.post()
                    .uri("/views/increase")
                    .body(views)
                    .retrieve()
                    .toBodilessEntity()

                countDownLatch.countDown()
            }
        }

        countDownLatch.await()

        val viewCount = restClient.get()
            .uri("/views/increase/{postId}/count", views.postId)
            .retrieve()
            .body(Long::class.java)

        assertEquals(1L, viewCount)
    }
}
