package in.ecom.service.impl;

import in.ecom.repository.AuthUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        if (username.contains("@"))
            return authUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email "+username));

        return authUserRepository.findByPhoneNumber(username).orElseThrow(() -> new UsernameNotFoundException("User not found with phone number "+username));

    }
}
