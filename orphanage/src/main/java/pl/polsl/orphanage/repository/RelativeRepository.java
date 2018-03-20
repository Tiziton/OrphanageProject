package pl.polsl.orphanage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.orphanage.domain.Relative;

import java.util.List;

/**
 * Spring Data JPA repository for the Relative entity.
 */
@Repository
public interface RelativeRepository extends CrudRepository<Relative, Long> {

    @Query("SELECT relative FROM Relative relative JOIN relative.fosterlings fosterling " +
            "WHERE fosterling.id = :fostrlingId")
    List<Relative> findAllByFosterling(@Param("fostrlingId") Long fosterlingId);

    List<Relative> findAllByLastname(String lastname);
}
