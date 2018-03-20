package pl.polsl.orphanage.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.orphanage.domain.Relative;
import pl.polsl.orphanage.service.dto.RelativeDTO;

/**
 * Mapper for the entity Relative and its DTO RelativeDTO.
 */
@Mapper(componentModel = "spring")
public interface RelativeMapper extends EntityMapper<RelativeDTO ,Relative> {

    @Mapping(target = "fosterlings", ignore = true)
    Relative toEntity(RelativeDTO relativeDTO);

    default Relative fromId(Long id) {
        if (id == null) {
            return null;
        }
        Relative relative = new Relative();
        relative.setId(id);
        return relative;
    }
}
