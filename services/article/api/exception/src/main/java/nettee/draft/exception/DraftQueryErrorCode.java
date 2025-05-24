package nettee.draft.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum DraftQueryErrorCode implements ErrorCode {
    DRAFT_NOT_FOUND("임시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DRAFT_GONE("더 이상 존재하지 않는 임시글입니다.", HttpStatus.GONE),
    DRAFT_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("임시글 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    DraftQueryErrorCode(String message, HttpStatus httpStatus) {
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
    public DraftQueryException exception() {
        return new DraftQueryException(this);
    }

    @Override
    public DraftQueryException exception(Throwable cause) {
        return new DraftQueryException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new DraftQueryException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new DraftQueryException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new DraftQueryException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new DraftQueryException(this, payload, cause);
    }
}
