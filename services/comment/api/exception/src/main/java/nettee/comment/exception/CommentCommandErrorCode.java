package nettee.comment.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum CommentCommandErrorCode implements ErrorCode {
    COMMENT_NOT_FOUND("댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    COMMENT_GONE("더 이상 존재하지 않는 댓글입니다.", HttpStatus.GONE),
    COMMENT_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("댓글 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    COMMENT_ALREADY_EXIST("댓글이 이미 존재합니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;

    CommentCommandErrorCode(String message, HttpStatus httpStatus) {
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
    public CommentCommandException exception() {
        return new CommentCommandException(this);
    }

    @Override
    public CommentCommandException exception(Throwable cause) {
        return new CommentCommandException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new CommentCommandException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new CommentCommandException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new CommentCommandException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new CommentCommandException(this, payload, cause);
    }
}
