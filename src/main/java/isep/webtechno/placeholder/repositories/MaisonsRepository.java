package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaisonsRepository extends JpaRepository<Maisons, Long> {

    List<Maisons> findByUser(User user);

}
