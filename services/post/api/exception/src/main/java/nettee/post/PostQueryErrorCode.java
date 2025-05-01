package nettee.post;

import java.util.Map;
import java.util.function.Supplier;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum PostQueryErrorCode implements ErrorCode {
    POST_NOT_FOUND("포스트를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    POST_GONE("더 이상 존재하지 않는 포스트입니다.", HttpStatus.GONE),
    POST_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("포스트 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    PostQueryErrorCode(String message, HttpStatus httpStatus) {
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
    public PostQueryException exception() {
        return new PostQueryException(this);
    }

    @Override
    public PostQueryException exception(Throwable cause) {
        return new PostQueryException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new PostQueryException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new PostQueryException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new PostQueryException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new PostQueryException(this, payload, cause);
    }
}
