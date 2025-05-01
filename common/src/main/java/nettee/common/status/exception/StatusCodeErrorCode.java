package nettee.common.status.exception;

import nettee.common.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public enum StatusCodeErrorCode implements ErrorCode {
    TOTAL_BITS_OVERFLOW(
            "각 영역의 크기 합이 %d 비트 이하여야 합니다.".formatted(Long.BYTES * 8),
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    GP_BITS_OUT_OF_BOUND(
            "설정한 general purpose 비트가 입력 허용 비트 범위를 벗어납니다.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    GP_BITS_NOT_DISTINCT(
            "Each input value must correspond to a distinct, non-overlapping bit.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    GP_CUSTOM_KEY_NOT_CORRECT(
            "정확한 GP 커스텀 이름을 입력하세요.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    SYS_INFO_OVERFLOW(
            "System information 비트가 입력 허용 비트 범위를 벗어납니다.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    CATEGORY_BITS_OVERFLOW(
            "Category 비트가 입력 허용 비트 범위를 벗어납니다.",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),
    INSTANCE_DETAIL_BITS_OVERFLOW(
            "Instance detail 비트가 입력 허용 비트 범위를 벗어납니다.",
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
