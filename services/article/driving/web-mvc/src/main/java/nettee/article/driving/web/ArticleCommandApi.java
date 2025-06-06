package nettee.article.driving.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nettee.article.application.usecase.ArticleCreateUseCase;
import nettee.article.application.usecase.ArticleDeleteUseCase;
import nettee.article.application.usecase.ArticleUpdateUseCase;
import nettee.article.domain.Article;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleCreateCommand;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleUpdateCommand;
import nettee.article.driving.web.dto.ArticleCommandDto.ArticleCommandResponse;
import nettee.article.driving.web.mapper.ArticleDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleCommandApi {
    private final ArticleCreateUseCase articleCreateUseCase;
    private final ArticleUpdateUseCase articleUpdateUseCase;
    private final ArticleDeleteUseCase articleDeleteUseCase;
    private final ArticleDtoMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleCommandResponse create(@RequestBody @Valid ArticleCreateCommand articleCreateCommand) {
        var article = Article.of(articleCreateCommand.title(), articleCreateCommand.content());
        return ArticleCommandResponse.builder()
                .article(articleCreateUseCase.createArticle(article))
                .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleCommandResponse updateArticle(
            @PathVariable("id") Long id,
            @RequestBody @Valid ArticleUpdateCommand articleUpdateCommand
    ) {
        var article = mapper.toDomain(id, articleUpdateCommand);

        return ArticleCommandResponse.builder()
                .article(articleUpdateUseCase.updateArticle(article))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable("id") Long id) {
        articleDeleteUseCase.deleteArticle(id);
    }

}
