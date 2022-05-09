package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationsRepository extends JpaRepository<Reservations,Long> {

}
