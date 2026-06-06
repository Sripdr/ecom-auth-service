package in.ecom.service.jwt_security;

import in.ecom.dao_entity.AuthUser;
import in.ecom.repository.AuthUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static in.ecom.util.CommonConstants.AUTH_HEADER;
import static in.ecom.util.CommonConstants.BEARER;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final AuthUserRepository authURepository;
    private final UserDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(AUTH_HEADER);
        if (header == null || !header.startsWith(BEARER)) {
            filterChain.doFilter(request,response);
            return;
        }

        String token = header.substring(7);
        String userId = jwtService.getId(token);
        if (SecurityContextHolder.getContext().getAuthentication() == null) {

            Optional<AuthUser> authUser = Optional.empty();

            if (userId != null) {
                authUser = authURepository.findById(userId);
            }

            if (authUser.isEmpty()) {
                String username = jwtService.getSubject(token);
                if (username != null) {
                    authUser = authURepository.findByEmailAndPhoneNumber(username, username);
                }
            }

            if (authUser.isPresent()) {
                AuthUser auth = authUser.get();
                if (jwtService.validateToken(token, auth)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(auth, null, auth.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request,response);
    }


}