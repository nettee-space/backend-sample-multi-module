package nettee.board.exception;

import nettee.common.CustomException;

import java.util.Map;
import java.util.function.Supplier;

public class BoardException extends CustomException {

    /**
     * BoardErrorCodeLazyHolder를 파라미터로 받기 위해, ErrorCode 타입으로 임시 설정함.
     */
    public BoardException(BoardErrorCode errorCode) {
        super(errorCode);
    }

    public BoardException(BoardErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public BoardException(BoardErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public BoardException(BoardErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public BoardException(BoardErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public BoardException(BoardErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
