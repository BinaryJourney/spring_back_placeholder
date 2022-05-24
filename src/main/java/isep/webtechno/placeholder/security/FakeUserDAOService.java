package isep.webtechno.placeholder.security;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static isep.webtechno.placeholder.security.UserRole.*;

@Repository("fake")
public class FakeUserDAOService implements UserProviderDAO{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeUserDAOService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserProvider> selectUserByUsername(String username) {
        return getUsers()
                .stream()
                .filter(UserProvider -> username.equals(UserProvider.getUsername()))
                .findFirst();
    }

    private List<UserProvider> getUsers() {
        List<UserProvider> users = Lists.newArrayList(
                new UserProvider(1L,
                        "root",
                        passwordEncoder.encode("root"),
                        Lists.newArrayList(new SimpleGrantedAuthority(ADMIN.name())),
                        true,
                        true,
                        true,
                        true
                ),
                new UserProvider(2L,
                        "user",
                        passwordEncoder.encode("user"),
                        Lists.newArrayList(new SimpleGrantedAuthority(USER.name())),
                        true,
                        true,
                        true,
                        true
                )
        );

        return users;
    }
}
