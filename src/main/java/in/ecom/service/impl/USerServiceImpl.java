package in.ecom.service.impl;

import in.ecom.client.UserInfoClient;
import in.ecom.dao_entity.AuthUser;
import in.ecom.dto.AuthUserResponse;
import in.ecom.dto.auth.AuthResponse;
import in.ecom.dto.userinfo.UserInfoResponse;
import in.ecom.exception.UserNotFound;
import in.ecom.mapper.UserMapper;
import in.ecom.repository.AuthUserRepository;
import in.ecom.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static in.ecom.util.CommonConstants.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class USerServiceImpl implements AuthUserService {
    private final UserInfoClient userInfoClient;
    private final AuthUserRepository repository;
    private final UserMapper mapper;

    @Override
    public List<AuthResponse> findAll() {
        List<AuthUser> all = repository.findAll();
        log.info("find All users");

        return all.stream().map(mapper::mapToAuthResponse).toList();
    }

    @Override
    public AuthResponse findUserById(String userId) {
        log.info("find UserById: {}", userId);
        AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFound(NOT_FOUND + userId));
        return mapper.mapToAuthResponse(authUser);
    }

    @Override
    public AuthUserResponse findByUserId(String userId) {
        log.info("find User By UserId: {}", userId);

        AuthUser authUser = repository.findById(userId).orElseThrow(() -> new UserNotFound(NOT_FOUND + userId));
        UserInfoResponse userInfoResponse = userInfoClient.getUserInfoByUserId(userId);

        log.info("find User By UserId: {}", authUser.getUserId());
        return new AuthUserResponse(mapper.mapToAuthResponse(authUser), userInfoResponse);
    }

    @Override
    public UserInfoResponse updateFirstName(String userId, String firstName) {
        log.info("update User By UserId: {}, {}", userId, firstName);
        return userInfoClient.updateFirstName(userId, firstName);
    }

    @Override
    public UserInfoResponse updateLastName(String userId, String lastName) {
        log.info("update User By UserId: {}, {}", userId, lastName);
        return userInfoClient.updateLastName(userId, lastName);
    }
}