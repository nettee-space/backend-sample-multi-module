package nettee.reply.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ReplyCommandErrorCode implements ErrorCode {
    REPLY_NOT_FOUND("답글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    REPLY_GONE("더 이상 존재하지 않는 답글입니다.", HttpStatus.GONE),
    REPLY_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("답글 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    REPLY_ALREADY_EXIST("답글이 이미 존재합니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;

    ReplyCommandErrorCode(String message, HttpStatus httpStatus) {
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
    public ReplyCommandException exception() {
        return new ReplyCommandException(this);
    }

    @Override
    public ReplyCommandException exception(Throwable cause) {
        return new ReplyCommandException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new ReplyCommandException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new ReplyCommandException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new ReplyCommandException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new ReplyCommandException(this, payload, cause);
    }
}
