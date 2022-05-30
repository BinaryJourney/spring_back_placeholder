package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.Reservations;
import isep.webtechno.placeholder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationsRepository extends JpaRepository<Reservations,Long> {
    List<Reservations> findByUser(User user);
}
