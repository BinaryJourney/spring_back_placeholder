package isep.webtechno.placeholder.services;

import isep.webtechno.placeholder.entities.Users;
import isep.webtechno.placeholder.exceptions.UsersNotFoundException;
import isep.webtechno.placeholder.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsersNotFoundException {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsersNotFoundException("User not present"));
        return user;
    }
    public void createUser(UserDetails user) {
        userRepository.save((Users) user);
    }
}
