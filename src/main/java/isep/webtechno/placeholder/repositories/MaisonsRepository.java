package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaisonsRepository extends JpaRepository<Maisons, Long> {
    List<Maisons> findByUser(User user);

    @Query(value = "select * from maisons s where s.titre like %:keyword% " +
            "or s.description like %:keyword%", nativeQuery = true)
    List<Maisons> findByKeyword(@Param("keyword") String keyword);
}
