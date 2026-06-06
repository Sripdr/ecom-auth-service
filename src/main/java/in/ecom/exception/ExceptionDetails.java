package in.ecom.exception;

import java.time.LocalDateTime;

public record ExceptionDetails(LocalDateTime timestamp, String path, String details) {
}
