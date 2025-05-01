package nettee.common.status.exception;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class StatusCodeException extends CustomException {
    public StatusCodeException(ErrorCode errorCode) {
        super(errorCode);
    }

    public StatusCodeException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public StatusCodeException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public StatusCodeException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public StatusCodeException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier) {
        super(errorCode, payloadSupplier);
    }

    public StatusCodeException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(errorCode, payloadSupplier, cause);
    }
}
