package nettee.comment;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.CustomException;

public class CommentQueryException extends CustomException {
    public CommentQueryException(CommentQueryErrorCode errorCode) {
        super(errorCode);
    }

    public CommentQueryException(CommentQueryErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public CommentQueryException(CommentQueryErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public CommentQueryException(CommentQueryErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public CommentQueryException(CommentQueryErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public CommentQueryException(CommentQueryErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
