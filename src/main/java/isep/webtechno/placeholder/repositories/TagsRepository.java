package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.Messages;
import isep.webtechno.placeholder.entities.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tags, Long> {
}
