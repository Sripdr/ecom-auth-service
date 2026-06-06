package in.ecom.controller;

import in.ecom.dto.AuthUserResponse;
import in.ecom.dto.auth.AuthResponse;
import in.ecom.dto.userinfo.UserInfoResponse;
import in.ecom.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static in.ecom.util.CommonConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/user")
@RequiredArgsConstructor

public class UserController  {
    private final AuthUserService authUserService;

    @GetMapping("/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<AuthResponse> findUserById(@PathVariable String userId) {
        AuthResponse authResponse = authUserService.findUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    @GetMapping("/userinfo/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<AuthUserResponse> findByUserId(@PathVariable String userId) {
        AuthUserResponse authUserResponse = authUserService.findByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(authUserResponse);
    }

    @PatchMapping("/first-name/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<UserInfoResponse> updateFirstName(@PathVariable String userId, @RequestBody String firstName) {
        UserInfoResponse userInfoResponse = authUserService.updateFirstName(userId, firstName);
        return ResponseEntity.status(HttpStatus.OK).body(userInfoResponse);
    }

    @PatchMapping("/last-name/{userId}")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<UserInfoResponse> updateLastName(@PathVariable String userId, @RequestBody String lastName) {
        UserInfoResponse userInfoResponse = authUserService.updateLastName(userId, lastName);
        return ResponseEntity.status(HttpStatus.OK).body(userInfoResponse);
    }
}
