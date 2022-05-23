package isep.webtechno.placeholder.security;

import java.util.Optional;

public interface UserProviderDAO {

    Optional<UserProvider> selectUserByUsername(String username);

}
