package in.ecom.controller;

import in.ecom.dto.auth.AuthUserRequest;
import in.ecom.dto.userinfo.UserInfoRequest;
import in.ecom.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthController authController;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Autowired
    @Test
    void testRegisterAdmin() {

        AuthUserRequest authUserRequest = new AuthUserRequest();
        authUserRequest.setEmail("admin@example.com");
        authUserRequest.setPassword("123456");
        UserInfoRequest userInfoRequest = new UserInfoRequest("123456", "firstname", "lastname", "fathername", "male", LocalDate.of(1980, 1, 1));
        authUserRequest.setUserInfoRequest(userInfoRequest);
        doNothing().when(authenticationService).registerAdmin(authUserRequest);

        ResponseEntity<String> response = authController.registerAdmin(authUserRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Admin Registered Successfully for the ADMIN@EXAMPLE.COM", response.getBody());
    }

}