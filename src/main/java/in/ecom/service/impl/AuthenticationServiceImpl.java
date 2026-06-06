package in.ecom.service.impl;

import in.ecom.client.UserInfoClient;
import in.ecom.dao_entity.AuthUser;
import in.ecom.dao_entity.Roles;
import in.ecom.dto.auth.AuthUserRequest;
import in.ecom.dto.login.LoginRequest;
import in.ecom.dto.login.LoginResponse;
import in.ecom.mapper.UserMapper;
import in.ecom.repository.AuthUserRepository;
import in.ecom.repository.RolesRepository;
import in.ecom.service.AuthenticationService;
import in.ecom.service.jwt_security.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthUserRepository userRepository;
    private final RolesRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;
    private final UserMapper mapper;
    private final UserInfoClient client;
    private final JWTService jwtService;

    @Override
    public void registerAdmin(AuthUserRequest userRequest) {
        log.info("register for the Admin: {}",userRequest.getEmail());
        AuthUser user = mapper.mapToAuthUser(userRequest);
        List<Roles> roles = roleRepository.findAll();

        if (userRepository.findByEmail(userRequest.getEmail()).isPresent())
            throw new IllegalArgumentException("Email already exists");
        if (userRepository.findByPhoneNumber(userRequest.getPhoneNumber()).isPresent())
            throw new IllegalArgumentException("Phone Number already exists");

       user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
       user.setRoles(roles);
        AuthUser save = userRepository.save(user);
        log.info("Admin registered successfully in Auth Service with userId:  {}",user.getUserId());

       userRequest.getUserInfoRequest().setUserId(save.getUserId());
       client.saveUserInfo(userRequest.getUserInfoRequest());
       log.info("Admin registered successfully in UserInfo Service with userId: {}",userRequest.getUserInfoRequest().getUserId());

    }

    @Override
    public void registerUser(AuthUserRequest userRequest) {
        log.info("register for the User: {}",userRequest.getEmail());
        AuthUser user = mapper.mapToAuthUser(userRequest);

        if (userRepository.findByEmail(userRequest.getEmail()).isPresent())
            throw new IllegalArgumentException("Email already exists");
        if (userRepository.findByPhoneNumber(userRequest.getPhoneNumber()).isPresent())
            throw new IllegalArgumentException("Phone Number already exists");

        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Roles role = roleRepository.findByRoleName("USER");
        user.setRoles(List.of(role));
        AuthUser save = userRepository.save(user);
        log.info("User registered successfully in Auth Service with userId: {}",save.getUserId());
        userRequest.getUserInfoRequest().setUserId(save.getUserId());

        client.saveUserInfo(userRequest.getUserInfoRequest());
        log.info("User registered successfully in UserInfo Service with userId: {}",userRequest.getUserInfoRequest().getUserId());

    }



    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        log.info("login for the User: {}",loginRequest.username());
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        if (!authenticate.isAuthenticated())
            throw new IllegalArgumentException("Invalid username or password");
       AuthUser user = (AuthUser) authenticate.getPrincipal();
        assert user != null;
        return new LoginResponse(LocalDateTime.now(),
                user.getUserId(),
                user.getEmail(),
                jwtService.generateToken(user));
    }
}
