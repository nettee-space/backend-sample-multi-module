package nettee.comment;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.CustomException;
import nettee.common.ErrorCode;

public class CommentCommandException extends CustomException {

    /**
     * CommentErrorCodeLazyHolder를 파라미터로 받기 위해, ErrorCode 타입으로 임시 설정함.
     */
    public CommentCommandException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CommentCommandException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public CommentCommandException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public CommentCommandException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public CommentCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public CommentCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
