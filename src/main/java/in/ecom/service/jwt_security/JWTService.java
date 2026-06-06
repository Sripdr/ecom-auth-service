package in.ecom.service.jwt_security;

import in.ecom.dao_entity.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class JWTService {

    @Value("${jwt.secret}")
    private  String secret;

    @Value("${jwt.expiration}")
    private  Long expiration;

    @Value("${jwt.issuer}")
    private  String issuer;



    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(AuthUser  user){

        Date now = new Date(System.currentTimeMillis());

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("phoneNumber", user.getPhoneNumber());
        claims.put("roles", user.getRoles().stream().map(r->"ROLE_"+r.getRoleName()).collect(Collectors.toList()));

       return Jwts.builder()
                .issuer(issuer)
                .id(user.getUserId())
                .subject(user.getUsername())
                .claims(claims)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    private Claims parseClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }

    public String getId(String token){
        return parseClaims(token).getId();
    }

    public boolean validateToken(String token,AuthUser  user){
        try {
            return getSubject(token).equals(user.getUsername()) && !parseClaims(token).getExpiration().before(new Date());
        }
        catch (Exception e){
        return false;
        }
    }

}
