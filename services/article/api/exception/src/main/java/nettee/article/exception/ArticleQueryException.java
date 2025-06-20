package nettee.article.exception;

import nettee.article.exception.ArticleQueryErrorCode;
import nettee.common.CustomException;

import java.util.Map;
import java.util.function.Supplier;

public class ArticleQueryException extends CustomException {
    public ArticleQueryException(ArticleQueryErrorCode errorCode) {
        super(errorCode);
    }

    public ArticleQueryException(ArticleQueryErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ArticleQueryException(ArticleQueryErrorCode errorCode, Runnable runnable) {
        super(errorCode, runnable);
    }

    public ArticleQueryException(ArticleQueryErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode, runnable, cause);
    }

    public ArticleQueryException(ArticleQueryErrorCode errorCode, Supplier<Map<String, Object>> payload) {
        super(errorCode, payload);
    }

    public ArticleQueryException(ArticleQueryErrorCode errorCode, Supplier<Map<String, Object>> payload, Throwable cause) {
        super(errorCode, payload, cause);
    }
}
