package in.ecom.controller;


import in.ecom.dto.auth.AuthUserRequest;
import in.ecom.dto.login.LoginRequest;
import in.ecom.dto.login.LoginResponse;
import in.ecom.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static in.ecom.util.CommonConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody AuthUserRequest userRequest) {
        authenticationService.registerAdmin(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin User Registered Successfully for the " + userRequest.getEmail().toUpperCase());
    }

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody AuthUserRequest userRequest) {
        authenticationService.registerUser(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully for the " + userRequest.getEmail().toUpperCase());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}
