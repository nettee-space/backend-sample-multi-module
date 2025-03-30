package nettee.exception.handler;

import nettee.common.CustomException;
import nettee.common.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import nettee.exception.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class) // 모든 커스텀 익셉션
    public ResponseEntity<?> handleCustomException(CustomException exception) {
        
        ErrorCode errorCode = exception.getErrorCode();
        
        exception.execute();
 
        var responseBody = ApiErrorResponse.builder()
                .status(errorCode.httpStatus().value())
                .code(errorCode.name())
                .message(exception.getMessage()) // same to errorCode.message
                .payload(exception.getPayload())
                .build();
        
        return ResponseEntity
                .status(errorCode.httpStatus())
                .body(responseBody);
    }
}
