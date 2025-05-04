package nettee.common;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;
import java.util.function.Supplier;

public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    private final Runnable runnable;
    private final Supplier<Map<String, Object>> payloadSupplier;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
        this.runnable = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
        this.runnable = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(ErrorCode errorCode, Runnable runnable) {
        super(errorCode.message());
        this.errorCode = errorCode;
        this.runnable = runnable;
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(ErrorCode errorCode, Runnable runnable, Throwable cause) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
        this.runnable = runnable;
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier) {
        super(errorCode.message());
        this.errorCode = errorCode;
        this.runnable = () -> {};
        this.payloadSupplier = payloadSupplier;
    }

    public CustomException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
        this.runnable = () -> {};
        this.payloadSupplier = payloadSupplier;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void execute() {
        runnable.run();
    }

    public Map<String, Object> getPayload() {
        return payloadSupplier.get();
    }

    private static final class Holder {
        private static final ErrorCode DEFAULT_ERROR_CODE = new ErrorCode() {
            @Override
            public String name() {
                return "Server Error";
            }

            @Override
            public String message() {
                return "An error occurred";
            }

            @Override
            public HttpStatus httpStatus() {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public CustomException exception() {
                return new CustomException(this);
            }

            @Override
            public CustomException exception(Throwable cause) {
                return new CustomException(this, cause);
            }

            @Override
            public CustomException exception(Runnable runnable) {
                return new CustomException(this, runnable);
            }

            @Override
            public CustomException exception(Runnable runnable, Throwable cause) {
                return new CustomException(this, runnable, cause);
            }

            @Override
            public CustomException exception(Supplier<Map<String, Object>> appendPayload) {
                return new CustomException(this, appendPayload);
            }

            @Override
            public CustomException exception(Supplier<Map<String, Object>> appendPayload, Throwable cause) {
                return new CustomException(this, appendPayload, cause);
            }
        };
    }
}
