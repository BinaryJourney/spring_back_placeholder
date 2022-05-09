package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.Tags;
import isep.webtechno.placeholder.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
