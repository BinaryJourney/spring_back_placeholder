package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.Images;
import isep.webtechno.placeholder.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
}
