package pl.polsl.orphanage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.orphanage.domain.Fosterling;

import java.util.Date;
import java.util.List;

/**
 * Spring Data JPA repository for the Fosterling entity.
 */
@Repository
public interface FosterlingRepository extends CrudRepository<Fosterling, Long> {

    List<Fosterling> findAll();

    @Query("SELECT fosterling FROM Fosterling fosterling WHERE fosterling.caretaker.id = :caretakerId")
    List<Fosterling> findAllByCaretaker(@Param("caretakerId") Long caretakerId);

    List<Fosterling> findAllByLastnameIgnoreCaseContaining(String lastname);

    @Query("SELECT fosterling FROM Fosterling fosterling " +
            "WHERE fosterling.dateBirth BETWEEN :fromDate and :toDate")
    List<Fosterling> findAllBetweenDate(@Param("fromDate") Date from,@Param("toDate") Date to);

    @Query("SELECT sibilings FROM Fosterling fosterling JOIN fosterling.sibilings sibilings " +
            "WHERE fosterling.id = :fosterlingId")
    List<Fosterling> findAllSibilings(@Param("fosterlingId") Long fosterlingId);

    Fosterling findOneById(Long id);
}
