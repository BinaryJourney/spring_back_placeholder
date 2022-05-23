package isep.webtechno.placeholder.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserProviderDAO userProviderDAO;

    @Autowired
    public UserService(@Qualifier("bd") UserProviderDAO userProviderDAO) {
        this.userProviderDAO = userProviderDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userProviderDAO.selectUserByUsername(username)
                .orElseThrow(()
                        -> new UsernameNotFoundException(String.format("Username/Email %s not found", username)));
    }
}
