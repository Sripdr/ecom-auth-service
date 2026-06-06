package in.ecom.service;

import in.ecom.dto.auth.AuthUserRequest;
import in.ecom.dto.login.LoginRequest;
import in.ecom.dto.login.LoginResponse;

public interface AuthenticationService {

    void registerAdmin(AuthUserRequest userRequest);

    void registerUser(AuthUserRequest userRequest);

    LoginResponse login(LoginRequest loginRequest);

}
