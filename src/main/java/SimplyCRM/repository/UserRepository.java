package SimplyCRM.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SimplyCRM.entities.User;
public interface UserRepository extends JpaRepository<User,Long>{

    @Query("SELECT x FROM User x Where x.email = ?1")
    Optional<User> findByEmail(String emailGiven);
}
