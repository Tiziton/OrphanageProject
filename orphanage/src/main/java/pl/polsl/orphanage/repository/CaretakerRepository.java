package pl.polsl.orphanage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.orphanage.domain.Caretaker;

import java.util.List;

/**
 * Spring Data JPA repository for the Caretaker entity.
 */
@Repository
public interface CaretakerRepository extends CrudRepository<Caretaker,Long> {

   @Query("SELECT caretaker FROM Caretaker caretaker WHERE caretaker.user = :userId")
    Caretaker findByUserId(@Param("userId")Long userId);

    List<Caretaker> findAll();
}
