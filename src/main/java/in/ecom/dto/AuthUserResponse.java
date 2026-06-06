package in.ecom.dto;

import in.ecom.dto.auth.AuthResponse;
import in.ecom.dto.userinfo.UserInfoResponse;

public record AuthUserResponse(
        AuthResponse  authResponse,
        UserInfoResponse userInfoResponse
) {
}
