package pl.polsl.orphanage.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.orphanage.service.FosterlingService;
import pl.polsl.orphanage.service.dto.FosterlingDTO;
import pl.polsl.orphanage.web.rest.errors.BadRequestAlertException;
import pl.polsl.orphanage.web.rest.requestbody.SibilingRequestBody;
import pl.polsl.orphanage.web.rest.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

/**
 * REST controller for managing Fosterling.
 */
@RestController
@RequestMapping("/api")
public class FosterlingController {

    private FosterlingService fosterlingService;
    private static final String ENTITY_NAME = "Fosterling";

    public FosterlingController(FosterlingService fosterlingService) {
        this.fosterlingService = fosterlingService;
    }

    /**
     * POST  /fosterling : Create a new caretaker.
     *
     * @param fosterlingDTO the fosterlingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fosterlingDTO, or with status 400 (Bad Request) if the fosterling has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fosterling")
    public ResponseEntity<FosterlingDTO> createFosterling(@RequestBody FosterlingDTO fosterlingDTO) throws URISyntaxException {
        if (fosterlingDTO.getId() != null) {
            throw new BadRequestAlertException("A new department cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FosterlingDTO result = fosterlingService.save(fosterlingDTO);
        return ResponseEntity.created(new URI("/api/caretaker/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /fosterling : Updates an existing department.
     *
     * @param fosterlingDTO the fosterlingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fosterlingDTO,
     * or with status 400 (Bad Request) if the fosterlingDTO is not valid,
     * or with status 500 (Internal Server Error) if the fosterlingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fosterling")
    public ResponseEntity<FosterlingDTO> updateFosterling(@RequestBody FosterlingDTO fosterlingDTO) throws URISyntaxException { ;
        if (fosterlingDTO.getId() == null) {
            return createFosterling(fosterlingDTO);
        }
        FosterlingDTO result = fosterlingService.save(fosterlingDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fosterlingDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET  /fosterling : get all fosterlings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fosterling in body
     */
    @GetMapping("/fosterling")
    public List<FosterlingDTO> getAllFosterling() {
        return fosterlingService.findAll();
    }

    @PostMapping("/search/fosterling")
    public List<FosterlingDTO> searchFosterling(
            @RequestBody(required = false) String lastname,
            @RequestBody(required = false) Date fromDate,
            @RequestBody(required = false) Date toDate
    ){
        if(fromDate != null && toDate != null){
            return fosterlingService.findFosterlingByBirthDate(fromDate,toDate);
        }else if (lastname != null){
            return fosterlingService.findFosterlingByName(lastname);
        } else {
            throw new BadRequestAlertException("Cannot find a request body", ENTITY_NAME, "noReqBody");
        }
    }

    /**
     * GET  /fosterling/:id : get fosterlings by  id.
     *
     * @return the ResponseEntity with status 200 (OK) and entity fosterling in body
     */
    @GetMapping("/fosterling/{id}")
    public FosterlingDTO getFosterlingById(@PathVariable Long id) {
        return fosterlingService.findById(id);
    }

    /**
     * GET  /fosterling/caretaker/:id : get fosterlings by caretaker id.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fosterling in body
     */
    @GetMapping("/fosterling/caretaker/{id}")
    public List<FosterlingDTO> getAllFosterlingByCaretaker(@PathVariable Long id) {
        return fosterlingService.findByCaretaker(id);
    }

    /**
     * DELETE  /fosterling/:id : delete the "id" fosterling.
     *
     * @param id the id of the fosterlingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fosterling/{id}")
    public ResponseEntity<Void> deleteFosterling(@PathVariable Long id) {
        fosterlingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * POST /sibiling/add : add sibiling to fosterling
     *
     * @param bodyRequest the body request
     */
    @PostMapping("/sibiling/add")
    public ResponseEntity<Void> addSibiling(@RequestBody SibilingRequestBody bodyRequest){
        fosterlingService.addFosterlingSibiling(bodyRequest.getId(), bodyRequest.getSibilingId());
        return  ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME,
                bodyRequest.getId().toString())).build();
    }

    /**
     * GET /sibiling/{id} : get sibilings of fosterling
     *
     * @param id id of the fosterling
     */
    @GetMapping("/sibiling/{id}")
    public List<FosterlingDTO> getSibilings(@PathVariable Long id){
        return fosterlingService.findFosterlingSibilings(id);
    }

    /**
     * POST /sibiling/delete : delete sibiling from fosterling
     *
     * @param bodyRequest the body request
     */
    @PostMapping("/sibiling/delete")
    public ResponseEntity<Void> deleteSibiling(@RequestBody SibilingRequestBody bodyRequest){
        fosterlingService.deleteFosterlingSibiling(bodyRequest.getId(), bodyRequest.getSibilingId());
        return  ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME,
                bodyRequest.getId().toString())).build();
    }


}
