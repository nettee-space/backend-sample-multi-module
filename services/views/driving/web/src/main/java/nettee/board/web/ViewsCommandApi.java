package nettee.board.web;

import lombok.RequiredArgsConstructor;
import nettee.views.Views;
import nettee.views.usecase.ViewsUpdateUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("views")
@RequiredArgsConstructor
public class ViewsCommandApi {
    private final ViewsUpdateUseCase viewsUpdateUseCase;

    @PostMapping("/increase")
    public void increase(@RequestBody Views views) {
        viewsUpdateUseCase.addViewCount(views);
    }
}
