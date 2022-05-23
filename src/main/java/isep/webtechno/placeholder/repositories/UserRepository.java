package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findFirstByEmail(String email);

}
