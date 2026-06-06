package in.ecom.dto.userinfo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserInfoResponse(Long id, String userId, String firstName, String lastName,
                               String fatherName, String gender, LocalDate dateOfBirth,
                               LocalDateTime createdDate, LocalDateTime updatedDate ) {
}
