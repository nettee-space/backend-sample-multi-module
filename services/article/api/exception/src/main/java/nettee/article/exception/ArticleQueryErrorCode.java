package nettee.article.exception;

import java.util.Map;
import java.util.function.Supplier;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ArticleQueryErrorCode implements ErrorCode {
    ARTICLE_NOT_FOUND("아티클을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ARTICLE_GONE("더 이상 존재하지 않는 아티클입니다.", HttpStatus.GONE),
    ARTICLE_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("아티클 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    ArticleQueryErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public ArticleQueryException exception() {
        return new ArticleQueryException(this);
    }

    @Override
    public ArticleQueryException exception(Throwable cause) {
        return new ArticleQueryException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new ArticleQueryException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new ArticleQueryException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new ArticleQueryException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new ArticleQueryException(this, payload, cause);
    }
}
