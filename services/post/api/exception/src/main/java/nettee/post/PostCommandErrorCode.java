package nettee.post;

import java.util.Map;
import java.util.function.Supplier;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum PostCommandErrorCode implements ErrorCode {
    POST_NOT_FOUND("포스트를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    POST_GONE("더 이상 존재하지 않는 게시물입니다.", HttpStatus.GONE),
    POST_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("포스트 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    POST_ALREADY_EXIST("포스트가 이미 존재합니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;

    PostCommandErrorCode(String message, HttpStatus httpStatus) {
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
    public PostCommandException exception() {
        return new PostCommandException(this);
    }

    @Override
    public PostCommandException exception(Throwable cause) {
        return new PostCommandException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new PostCommandException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new PostCommandException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new PostCommandException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new PostCommandException(this, payload, cause);
    }
}
