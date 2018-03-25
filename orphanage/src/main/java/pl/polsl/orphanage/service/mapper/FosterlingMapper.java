package pl.polsl.orphanage.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.orphanage.domain.Fosterling;
import pl.polsl.orphanage.service.dto.FosterlingDTO;

/**
 * Mapper for the entity Fosterling and its DTO FosterlingDTO.
 */
@Mapper(componentModel = "spring", uses = CaretakerMapper.class)
public interface FosterlingMapper extends EntityMapper<FosterlingDTO,Fosterling>{

    @Mapping(source="caretaker.id", target = "caretakerId")
    FosterlingDTO toDto(Fosterling fosterling);

    @Mapping(source = "caretakerId", target = "caretaker")
    @Mapping(target = "sibilings", ignore = true)
    @Mapping(target = "holidays", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "rewards", ignore = true)
    Fosterling toEntity(FosterlingDTO fosterlingDTO);

    default Fosterling fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fosterling fosterling = new Fosterling();
        fosterling.setId(id);
        return fosterling;
    }
}
