package nettee.article.exception;

import java.util.Map;
import java.util.function.Supplier;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ArticleCommandErrorCode implements ErrorCode {
    ARTICLE_NOT_FOUND("아티클을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ARTICLE_GONE("더 이상 존재하지 않는 게시물입니다.", HttpStatus.GONE),
    ARTICLE_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("아티클 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    ARTICLE_ALREADY_EXIST("아티클이 이미 존재합니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;

    ArticleCommandErrorCode(String message, HttpStatus httpStatus) {
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
    public ArticleCommandException exception() {
        return new ArticleCommandException(this);
    }

    @Override
    public ArticleCommandException exception(Throwable cause) {
        return new ArticleCommandException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new ArticleCommandException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new ArticleCommandException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new ArticleCommandException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new ArticleCommandException(this, payload, cause);
    }
}
