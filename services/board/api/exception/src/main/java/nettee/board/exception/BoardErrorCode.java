package nettee.board.exception;

import java.util.Map;
import java.util.function.Supplier;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

public enum BoardErrorCode implements ErrorCode {
    BOARD_NOT_FOUND("게시물을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    BOARD_GONE("더 이상 존재하지 않는 게시물입니다.", HttpStatus.GONE),
    BOARD_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("게시물 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    BOARD_ALREADY_EXIST("게시물이 이미 존재합니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;

    BoardErrorCode(String message, HttpStatus httpStatus) {
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
    public BoardException exception() {
        return new BoardException(this);
    }

    @Override
    public BoardException exception(Throwable cause) {
        return new BoardException(this, cause);
    }

    @Override
    public RuntimeException exception(Runnable runnable) {
        return new BoardException(this, runnable);
    }

    @Override
    public RuntimeException exception(Runnable runnable, Throwable cause) {
        return new BoardException(this, runnable, cause);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload) {
        return new BoardException(this, payload);
    }

    @Override
    public RuntimeException exception(Supplier<Map<String, Object>> payload, Throwable cause) {
        return new BoardException(this, payload, cause);
    }
}
