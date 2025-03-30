package nettee.common;

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
}
