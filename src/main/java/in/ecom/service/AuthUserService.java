package in.ecom.service;

import in.ecom.dto.AuthUserResponse;
import in.ecom.dto.auth.AuthResponse;
import in.ecom.dto.userinfo.UserInfoResponse;

import java.util.List;

public interface AuthUserService {

    List<AuthResponse> findAll();

    AuthResponse findUserById(String userId);

    AuthUserResponse findByUserId(String userId);

    UserInfoResponse updateFirstName(String userId, String firstName);

    UserInfoResponse updateLastName(String userId, String lastName);
}
