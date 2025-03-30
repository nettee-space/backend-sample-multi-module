package me.nettee;

import java.util.Map;
import java.util.function.Supplier;

public class BoardCommandException extends CustomException {

    /**
     * BoardErrorCodeLazyHolder를 파라미터로 받기 위해, ErrorCode 타입으로 임시 설정함.
     */
    public BoardCommandException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BoardCommandException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public BoardCommandException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public BoardCommandException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public BoardCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public BoardCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
