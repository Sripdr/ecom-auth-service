package in.ecom.dto.auth;

import in.ecom.dto.userinfo.UserInfoRequest;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthUserRequest {
    private String email;
    @Size(min = 10, max = 10, message = "Phone Number must be 10 digits long")
    private String phoneNumber;
    private String password;
    private UserInfoRequest userInfoRequest;

}
