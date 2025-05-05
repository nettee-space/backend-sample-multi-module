package nettee.board.web;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ViewsApiTest {
    RestClient restClient = RestClient.create("http://localhost:5001");

    @Test
    void viewIncreaseTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        Long postId = 1L;
        Long userId = 1L;

        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                restClient.post()
                        .uri("/views/increase/{postId}/{userId}", postId, userId)
                        .retrieve();

                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        Long viewCount = restClient.post()
                .uri("/views/increase/{postId}/count", postId)
                .retrieve()
                .body(Long.class);

        System.out.println("viewCount = " + viewCount);
    }
}
