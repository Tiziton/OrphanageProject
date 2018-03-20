package pl.polsl.orphanage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.orphanage.domain.Holiday;

import java.util.List;

/**
 * Spring Data JPA repository for the Holiday entity.
 */
@Repository
public interface HolidayRepository extends CrudRepository<Holiday, Long>{

    @Query("SELECT holiday FROM Holiday holiday WHERE holiday.fosterling.id = :fosterlingId")
    List<Holiday> findByFosterlingId(@Param("fosterlingId") Long fosterlingId);
}
