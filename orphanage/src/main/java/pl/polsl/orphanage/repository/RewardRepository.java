package pl.polsl.orphanage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.orphanage.domain.Reward;

import java.util.List;

/**
 * Spring Data JPA repository for the Reward entity.
 */
@Repository
public interface RewardRepository extends CrudRepository<Reward, Long> {

    @Query("SELECT reward FROM Reward reward WHERE reward.fosterling.id = :fosterlingId")
    List<Reward> findByFosterlingId(@Param("fosterlingId") Long fosterlingId);
}
