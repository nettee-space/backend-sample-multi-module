package nettee.draft;

import nettee.common.CustomException;

import java.util.Map;
import java.util.function.Supplier;

public class DraftQueryException extends CustomException {
    public DraftQueryException(DraftQueryErrorCode errorCode) {
        super(errorCode);
    }

    public DraftQueryException(DraftQueryErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DraftQueryException(DraftQueryErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public DraftQueryException(DraftQueryErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public DraftQueryException(DraftQueryErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public DraftQueryException(DraftQueryErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
