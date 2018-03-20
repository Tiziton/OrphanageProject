package pl.polsl.orphanage.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.orphanage.domain.Caretaker;
import pl.polsl.orphanage.service.dto.CaretakerDTO;

/**
 * Mapper for the entity Caretaker and its DTO CaretakerDTO.
 */
@Mapper(componentModel = "spring")
public interface CaretakerMapper extends EntityMapper<CaretakerDTO,Caretaker> {

    @Mapping(source = "user.id", target = "userId")
    CaretakerDTO toDto(Caretaker caretaker);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "fosterlings", ignore = true)
    Caretaker toEntity(CaretakerDTO caretakerDTO);

    default Caretaker fromId(Long id) {
        if (id == null) {
            return null;
        }
        Caretaker caretaker = new Caretaker();
        caretaker.setId(id);
        return caretaker;
    }
}
