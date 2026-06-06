package in.ecom.util;

public class CommonConstants {

    public static final String BASE_URL = "/api/ecom/auth-service";
    public static final String ADMIN_URL = BASE_URL + "/admin/**";
    public static final String USER_URL = BASE_URL + "/user/**";
    public static final String[] PUBLIC_URL = {
            BASE_URL + "/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/actuator/**",
    };
    public static final String NOT_FOUND = "User Not Found with Given UserId ";
    public static final String BEARER= "Bearer ";
    public static final String AUTH_HEADER = "Authorization";
}
