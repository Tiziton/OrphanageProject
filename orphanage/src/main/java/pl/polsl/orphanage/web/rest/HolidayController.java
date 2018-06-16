package pl.polsl.orphanage.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.orphanage.domain.Holiday;
import pl.polsl.orphanage.repository.HolidayRepository;
import pl.polsl.orphanage.service.dto.HolidayDTO;
import pl.polsl.orphanage.service.mapper.HolidayMapper;
import pl.polsl.orphanage.web.rest.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.WeekFields.ISO;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * REST controller for managing Holiday.
 */
@RestController
@RequestMapping("/api")
public class HolidayController {

    private static final String ENTITY_NAME = "Holiday";

    private HolidayRepository holidayRepository;

    private HolidayMapper holidayMapper;

    HolidayController(HolidayRepository holidayRepository, HolidayMapper holidayMapper){
        this.holidayMapper = holidayMapper;
        this.holidayRepository = holidayRepository;
    }

    /**
     * POST  /holidays : Create a new holiday.
     *
     * @param holidayDTO the holidayDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new holidayDTO, or with status 400 (Bad Request) if the holiday has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/holidays")
    public ResponseEntity<HolidayDTO> createHoliday(@RequestBody HolidayDTO holidayDTO) throws URISyntaxException {

        Holiday holiday = holidayMapper.toEntity(holidayDTO);
        holiday = holidayRepository.save(holiday);
        HolidayDTO result = holidayMapper.toDto(holiday);
        return ResponseEntity.created(new URI("/api/holidays/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /holidays : Updates an existing document.
     *
     * @param holidayDTO the holidayDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated holidayDTO,
     * or with status 400 (Bad Request) if the holidayDTO is not valid,
     * or with status 500 (Internal Server Error) if the holidayDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/holidays")
    public ResponseEntity<HolidayDTO> updateHoliday(@RequestBody HolidayDTO holidayDTO) throws URISyntaxException {
        if (holidayDTO.getId() == null) {
            return createHoliday(holidayDTO);
        }
        Holiday holiday = holidayMapper.toEntity(holidayDTO);
        holiday = holidayRepository.save(holiday);
        HolidayDTO result = holidayMapper.toDto(holiday);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, holidayDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET  /holidays/:fosterlingId : get all the holidays.
     *
     * @param fosterlingId the id of the fosterling parent
     * @return the ResponseEntity with status 200 (OK) and the list of holidays in body
     */
    @GetMapping("/holidays/{fosterlingId}")
    public ResponseEntity<List<HolidayDTO>> getAllHoliday(@PathVariable Long fosterlingId) {
        List<Holiday> list = holidayRepository.findByFosterlingId(fosterlingId);
        return new ResponseEntity<>(holidayMapper.toDto(list), HttpStatus.OK);
    }
    
    /**
     * GET  /holidays/return/:returned : get all holidays returned or not returned
     *
     * @param returned signalise if fosterling returned
     * @return the ResponseEntity with status 200 (OK) and the list of holidays in body
     */
    @GetMapping("/holidays/return/{returned}")
    public ResponseEntity<List<HolidayDTO>> getReturnedHoliday(@PathVariable Boolean returned) {
       short ret=(short)((returned==true)? 1:0);
       List<Holiday> list = holidayRepository.findReturned(ret);
        return new ResponseEntity<>(holidayMapper.toDto(list), HttpStatus.OK);
    }
    
    
    /**
     * GET  /holidays/present?startDate=:startDate&endDate=:endDate : get all the holidays of fosterlings present in period from
     * startDate to endDate date
     *
     * @param fosterlingId the id of the fosterling parent
     * @return the ResponseEntity with status 200 (OK) and the list of holidays in body
     */
    @GetMapping("/holidays/present")
    public ResponseEntity<List<HolidayDTO>> getDatesHoliday(
            @RequestParam("startDate")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        
        Date from = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        List<Holiday> list = holidayRepository.findPresent(from, to);
        return new ResponseEntity<>(holidayMapper.toDto(list), HttpStatus.OK);
        
    }
    
    
    /**
     * DELETE  /holidays/:id : delete the "id" holiday.
     *
     * @param id the id of the holidayDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/holidays/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        holidayRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
