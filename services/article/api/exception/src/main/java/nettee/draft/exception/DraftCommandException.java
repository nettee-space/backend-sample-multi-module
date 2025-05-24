package nettee.draft.exception;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class DraftCommandException extends CustomException {

    public DraftCommandException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DraftCommandException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DraftCommandException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public DraftCommandException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public DraftCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public DraftCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
