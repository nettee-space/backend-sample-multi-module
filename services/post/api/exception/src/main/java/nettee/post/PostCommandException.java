package nettee.post;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class PostCommandException extends CustomException {

    public PostCommandException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PostCommandException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public PostCommandException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public PostCommandException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public PostCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public PostCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
