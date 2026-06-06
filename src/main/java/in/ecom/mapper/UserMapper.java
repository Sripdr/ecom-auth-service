package in.ecom.mapper;

import in.ecom.dao_entity.AuthUser;
import in.ecom.dto.auth.AuthResponse;
import in.ecom.dto.auth.AuthUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    AuthUser mapToAuthUser(AuthUserRequest userRequest);

    AuthResponse mapToAuthResponse(AuthUser authUser);
}
