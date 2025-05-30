package nettee.board.web;

import lombok.RequiredArgsConstructor;
import nettee.views.usecase.ViewsReadUseCase;
import nettee.views.usecase.ViewsUpdateUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("views")
@RequiredArgsConstructor
public class ViewsQueryApi {
    private final ViewsReadUseCase viewsReadUseCase;

    @GetMapping("/increase/{postId}/count")
    public Long viewCount(@PathVariable Long postId) {
        return viewsReadUseCase.getViews(postId);
    }
}
