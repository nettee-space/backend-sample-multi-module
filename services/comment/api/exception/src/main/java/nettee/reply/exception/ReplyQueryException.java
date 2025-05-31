package nettee.reply.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.CustomException;

public class ReplyQueryException extends CustomException {
    public ReplyQueryException(ReplyQueryErrorCode errorCode) {
        super(errorCode);
    }

    public ReplyQueryException(ReplyQueryErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ReplyQueryException(ReplyQueryErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public ReplyQueryException(ReplyQueryErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public ReplyQueryException(ReplyQueryErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public ReplyQueryException(ReplyQueryErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
