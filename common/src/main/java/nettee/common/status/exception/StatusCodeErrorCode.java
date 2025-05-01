package nettee.common.status.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum StatusCodeErrorCode implements ErrorCode {
    GP_BITS_OUT_OF_BOUND(
            "설정한 general purpose 비트가 허용 비트 범위를 벗어납니다.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    GP_BITS_NOT_DISTINCT(
            "Each input value must correspond to a distinct, non-overlapping bit.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    TOTAL_BITS_OVERFLOW(
            "각 영역의 크기 합이 %d 비트 이하여야 합니다.".formatted(Long.BYTES * 8),
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    ;

    private final String message;
    private final HttpStatus httpStatus;

    StatusCodeErrorCode(String message, HttpStatus httpStatus) {
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
    public StatusCodeException exception() {
        return new StatusCodeException(this);
    }

    @Override
    public StatusCodeException exception(Throwable cause) {
        return new StatusCodeException(this, cause);
    }

    @Override
    public StatusCodeException exception(Runnable runnable) {
        return new StatusCodeException(this, runnable);
    }

    @Override
    public StatusCodeException exception(Runnable runnable, Throwable cause) {
        return new StatusCodeException(this, runnable, cause);
    }

    @Override
    public StatusCodeException exception(Supplier<Map<String, Object>> appendPayload) {
        return new StatusCodeException(this, appendPayload);
    }

    @Override
    public StatusCodeException exception(Supplier<Map<String, Object>> appendPayload, Throwable cause) {
        return new StatusCodeException(this, appendPayload, cause);
    }
}
