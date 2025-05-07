package nettee.article;

import nettee.common.CustomException;
import nettee.common.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class ArticleCommandException extends CustomException {

    public ArticleCommandException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ArticleCommandException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ArticleCommandException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public ArticleCommandException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public ArticleCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public ArticleCommandException(ErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
