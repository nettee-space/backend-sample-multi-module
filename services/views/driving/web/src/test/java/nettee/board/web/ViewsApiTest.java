package nettee.board.web;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import nettee.views.Views;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ViewsApiTest {
    RestClient restClient = RestClient.create("http://localhost:5001");

    @Test
    void viewIncreaseTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        Views views = Views.builder()
                .postId(1L)
                .userId(1L)
                .build();

        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                restClient.post()
                        .uri("/views/increase")
                        .body(views)
                        .retrieve()
                        .toBodilessEntity();

                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        Long viewCount = restClient.post()
                .uri("/views/increase/{postId}/count", views.getPostId())
                .retrieve()
                .body(Long.class);

        // 조회 어뷰징 방지로, 조회수 증가는 한 번만 수행되어야 함
        System.out.println("viewCount = " + viewCount);
        Assertions.assertEquals(1L, viewCount);
    }
}
