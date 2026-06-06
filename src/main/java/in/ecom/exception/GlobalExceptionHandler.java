package in.ecom.exception;

import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ExceptionDetails> handleUerNotFoundException(Exception e, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(), request.getDescription(false), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDetails);

    }

    @ExceptionHandler(Exception.class)
    public ExceptionDetails handleException(Exception e, WebRequest request) {
        return new ExceptionDetails(LocalDateTime.now(), request.getDescription(false), e.getMessage());
    }
}
