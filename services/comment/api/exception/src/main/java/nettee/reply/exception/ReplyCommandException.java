package nettee.reply.exception;

import java.util.Map;
import java.util.function.Supplier;
import nettee.common.CustomException;
import nettee.common.ErrorCode;

public class ReplyCommandException extends CustomException {

    /**
     * ReplyErrorCodeLazyHolder를 파라미터로 받기 위해, ErrorCode 타입으로 임시 설정함.
     */
    public ReplyCommandException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ReplyCommandException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ReplyCommandException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public ReplyCommandException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public ReplyCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public ReplyCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
