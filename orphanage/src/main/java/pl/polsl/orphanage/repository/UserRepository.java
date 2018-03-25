package pl.polsl.orphanage.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.orphanage.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    List<User> findAll();

    @Transactional()
    @Modifying
    @Query("DELETE FROM User WHERE id IS NOT 1")
    void deleteAllByExceptSystemUser();
}
