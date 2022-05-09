package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.Commentaires;
import isep.webtechno.placeholder.entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images, Long> {
}
