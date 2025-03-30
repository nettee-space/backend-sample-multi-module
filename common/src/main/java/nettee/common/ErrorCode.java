package nettee.common;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public interface ErrorCode {
    String name();
    String message();

    HttpStatus httpStatus();
    RuntimeException exception();
    RuntimeException exception(Throwable cause);

    RuntimeException exception(Runnable runnable);
    RuntimeException exception(Runnable runnable, Throwable cause);

    RuntimeException exception(Supplier<Map<String, Object>> appendPayload);
    RuntimeException exception(Supplier<Map<String, Object>> appendPayload, Throwable cause);
}
