package nettee.comment.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum CommentQueryErrorCode implements ErrorCode {
    COMMENT_NOT_FOUND("댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    COMMENT_GONE("더 이상 존재하지 않는 댓글입니다.", HttpStatus.GONE),
    COMMENT_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("댓글 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    CommentQueryErrorCode(String message, HttpStatus httpStatus) {
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
    public CommentQueryException exception() {
        return new CommentQueryException(this);
    }

    @Override
    public CommentQueryException exception(Throwable cause) {
        return new CommentQueryException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new CommentQueryException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new CommentQueryException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new CommentQueryException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new CommentQueryException(this, payload, cause);
    }
}
