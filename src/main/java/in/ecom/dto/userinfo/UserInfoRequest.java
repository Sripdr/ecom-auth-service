package in.ecom.dto.userinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {
    private String userId;

    private String firstName;

    private String lastName;

    private String fatherName;

    private String gender;

    private LocalDate dateOfBirth;
}
