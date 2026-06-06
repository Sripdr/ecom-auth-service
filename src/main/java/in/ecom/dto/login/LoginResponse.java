package in.ecom.dto.login;

import java.time.LocalDateTime;

public record LoginResponse(
        LocalDateTime currentDateAndTime,
      String userId,
       String username,
        String token) {
}
