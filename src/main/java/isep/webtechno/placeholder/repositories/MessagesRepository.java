package isep.webtechno.placeholder.repositories;

import isep.webtechno.placeholder.entities.Images;
import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessagesRepository extends JpaRepository<Messages, Long> {

    @Query(value = "select * from messages s where (s.receiving_user_id=:uid1 and s.sending_user_id=:uid2)" +
            "or (s.receiving_user_id=:uid2 and s.sending_user_id=:uid1)", nativeQuery = true)
    List<Messages> findByUsersId(@Param("uid1") Long uid1, @Param("uid2") Long uid2);
}
