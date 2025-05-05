package nettee.board.web;

import lombok.RequiredArgsConstructor;
import nettee.views.usecase.ViewsUpdateUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("views")
@RequiredArgsConstructor
public class ViewsCommandApi {
    private final ViewsUpdateUseCase viewsUpdateUseCase;

    @PostMapping("/increase/{postId}/{userId}")
    public void increase(
            @PathVariable Long postId,
            @PathVariable Long userId
    ) {
        viewsUpdateUseCase.addViewCount(postId, userId);
    }
}
