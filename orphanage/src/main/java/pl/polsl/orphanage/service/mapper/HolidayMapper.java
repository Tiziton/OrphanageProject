package pl.polsl.orphanage.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.orphanage.domain.Holiday;
import pl.polsl.orphanage.service.dto.HolidayDTO;

/**
 * Mapper for the entity Holiday and its DTO HolidayDTO.
 */
@Mapper(componentModel = "spring", uses = FosterlingMapper.class)
public interface HolidayMapper extends EntityMapper<HolidayDTO,Holiday>{

    @Mapping(source="fosterling.id", target = "fosterlingId")
    HolidayDTO toDto(Holiday holiday);

    @Mapping(source = "fosterlingId", target = "fosterling.id")
    Holiday toEntity(HolidayDTO holidayDTO);

    default Holiday fromId(Long id) {
        if (id == null) {
            return null;
        }
        Holiday holiday = new Holiday();
        holiday.setId(id);
        return holiday;
    }
}
