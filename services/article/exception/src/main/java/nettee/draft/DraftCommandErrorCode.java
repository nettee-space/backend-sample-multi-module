package nettee.draft;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum DraftCommandErrorCode implements ErrorCode {
    DRAFT_NOT_FOUND("임시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DRAFT_GONE("더 이상 존재하지 않는 게시물입니다.", HttpStatus.GONE),
    DRAFT_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("임시글 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    DRAFT_ALREADY_EXIST("임시글이 이미 존재합니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;

    DraftCommandErrorCode(String message, HttpStatus httpStatus) {
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
    public DraftCommandException exception() {
        return new DraftCommandException(this);
    }

    @Override
    public DraftCommandException exception(Throwable cause) {
        return new DraftCommandException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new DraftCommandException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new DraftCommandException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new DraftCommandException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new DraftCommandException(this, payload, cause);
    }
}
