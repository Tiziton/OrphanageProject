package pl.polsl.orphanage.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.orphanage.domain.Reward;
import pl.polsl.orphanage.service.dto.RewardDTO;

/**
 * Mapper for the entity Reward and its DTO RewardDTO.
 */
@Mapper(componentModel = "spring", uses = FosterlingMapper.class)
public interface RewardMapper extends EntityMapper<RewardDTO,Reward>{

    @Mapping(source="fosterling.id", target = "fosterlingId")
    RewardDTO toDto(Reward reward);

    @Mapping(source = "fosterlingId", target = "fosterling")
    Reward toEntity(RewardDTO rewardDTO);

    default Reward fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reward reward = new Reward();
        reward.setId(id);
        return reward;
    }
}
