package isep.webtechno.placeholder.security;

import isep.webtechno.placeholder.repositories.UsersRepository;
import isep.webtechno.placeholder.services.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider {
//    @Autowired private SecurityUserDetailsService userDetailsService;
//    @Autowired private PasswordEncoder passwordEncoder;
//    @Autowired private UsersRepository userRepository;
//
//    @Override
//    public Authentication authenticate(Authentication authentication)
//            throws AuthenticationException {
//        String username = authentication.getName();
//    }
//
//    @Override public boolean supports(Class<?> authentication) {
//        return true;
//    }
}
