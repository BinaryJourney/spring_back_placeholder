package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.Tags;
import isep.webtechno.placeholder.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);




}
