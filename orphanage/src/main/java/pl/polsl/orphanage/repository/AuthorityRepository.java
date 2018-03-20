package pl.polsl.orphanage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.orphanage.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
@Repository
public interface AuthorityRepository extends CrudRepository<Authority, String> {

    Authority findOneByName(String name);
}
