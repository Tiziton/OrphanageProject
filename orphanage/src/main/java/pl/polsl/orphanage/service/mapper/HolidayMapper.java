package pl.polsl.orphanage.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.orphanage.domain.Holiday;
import pl.polsl.orphanage.service.dto.HolidayDTO;
import pl.polsl.orphanage.service.mapper.annotations.BitToBoolean;
import pl.polsl.orphanage.service.mapper.annotations.BooleanBitFormater;
import pl.polsl.orphanage.service.mapper.annotations.BooleanToBit;
import pl.polsl.orphanage.service.mapper.helper.BooleanFormater;

/**
 * Mapper for the entity Holiday and its DTO HolidayDTO.
 */
@Mapper(componentModel = "spring", uses = {FosterlingMapper.class, BooleanFormater.class})
public interface HolidayMapper extends EntityMapper<HolidayDTO,Holiday>{


    @Mapping(target = "isReturned", source="isReturned",
            qualifiedBy = {BooleanBitFormater.class, BitToBoolean.class})
    @Mapping(source="fosterling.id", target = "fosterlingId")
    HolidayDTO toDto(Holiday holiday);

    @Mapping(target = "isReturned", source="isReturned",
            qualifiedBy = {BooleanBitFormater.class, BooleanToBit.class})
    @Mapping(source = "fosterlingId", target = "fosterling")
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
