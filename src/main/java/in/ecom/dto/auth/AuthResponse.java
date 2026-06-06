package in.ecom.dto.auth;

import in.ecom.dao_entity.Roles;

import java.util.List;

public record AuthResponse(
        String userId,
        String email,
        String phoneNumber,
        List<Roles> roles
) {
}
