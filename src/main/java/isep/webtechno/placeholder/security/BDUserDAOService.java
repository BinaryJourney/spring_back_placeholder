package isep.webtechno.placeholder.security;

import com.google.common.collect.Lists;
import isep.webtechno.placeholder.entities.User;
import isep.webtechno.placeholder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static isep.webtechno.placeholder.security.UserRole.ADMIN;

@Repository("bd")
public class BDUserDAOService implements UserProviderDAO{

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public BDUserDAOService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserProvider> selectUserByUsername(String username) {
        User user = userRepository.findFirstByEmail(username).orElseThrow(()
                -> new UsernameNotFoundException(String.format("Username/Email %s not found", username)));
        Optional<UserProvider> userProvider = Optional.of(new UserProvider(user.getId(), user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                Lists.newArrayList(new SimpleGrantedAuthority(user.getRole())),
                true,
                true,
                true,
                true));
        return userProvider;
    }
}
