package pl.polsl.orphanage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.orphanage.domain.Document;

import java.util.List;

/**
 * Spring Data JPA repository for the Document entity.
 */
@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

    @Query("SELECT document FROM Document document WHERE document.fosterling.id = :fosterlingId")
    List<Document> findByFosterlingId(@Param("fosterlingId") Long fosterlingId);
}
