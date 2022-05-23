package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.User_bak;
import isep.webtechno.placeholder.security.UserProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User_bak, Long> {
    Optional<User_bak> findByEmail(String email);

}
