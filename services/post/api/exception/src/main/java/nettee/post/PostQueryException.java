package nettee.post;

import nettee.common.CustomException;

import java.util.Map;
import java.util.function.Supplier;

public class PostQueryException extends CustomException {
    public PostQueryException(PostQueryErrorCode errorCode) {
        super(errorCode);
    }

    public PostQueryException(PostQueryErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public PostQueryException(PostQueryErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public PostQueryException(PostQueryErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public PostQueryException(PostQueryErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public PostQueryException(PostQueryErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
