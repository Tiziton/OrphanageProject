package pl.polsl.orphanage.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.orphanage.service.CaretakerService;
import pl.polsl.orphanage.service.dto.CaretakerDTO;
import pl.polsl.orphanage.web.rest.errors.BadRequestAlertException;
import pl.polsl.orphanage.web.rest.util.HeaderUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing Caretaker.
 */
@RestController
@RequestMapping("/api")
public class CaretakerController {

    private CaretakerService caretakerService;
    private static final String ENTITY_NAME = "Caretaker";

    public CaretakerController(CaretakerService caretakerService) {
        this.caretakerService = caretakerService;
    }

    /**
     * POST  /caretaker : Create a new caretaker.
     *
     * @param caretakerDTO the caretakerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caretakerDTO, or with status 400 (Bad Request) if the caretaker has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/caretaker")
    public ResponseEntity<CaretakerDTO> createCaretaker(@RequestBody CaretakerDTO caretakerDTO) throws URISyntaxException {
        if (caretakerDTO.getId() != null) {
            throw new BadRequestAlertException("A new department cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaretakerDTO result = caretakerService.save(caretakerDTO);
        return ResponseEntity.created(new URI("/api/caretaker/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /caretaker : Updates an existing department.
     *
     * @param caretakerDTO the caretakerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated departmentDTO,
     * or with status 400 (Bad Request) if the departmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the departmentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/caretaker")
    public ResponseEntity<CaretakerDTO> updateCaretaker(@RequestBody CaretakerDTO caretakerDTO) throws URISyntaxException { ;
        if (caretakerDTO.getId() == null) {
            return createCaretaker(caretakerDTO);
        }
        CaretakerDTO result = caretakerService.save(caretakerDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caretakerDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET  /caretaker/:id : get caretaker by user.
     *
     * @param id the id of caretaker entity
     * @return the ResponseEntity with status 200 (OK) and the list of caretaker in body
     */
    @GetMapping("/caretaker/{id}")
    public CaretakerDTO getAllCaretakerByUser(@PathVariable Long id) {
        return caretakerService.findCaretakerByUserId(id);
    }

    /**
     * DELETE  /caretaker/:id : delete the "id" caretaker.
     *
     * @param id the id of the caretakerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/caretaker/{id}")
    public ResponseEntity<Void> deleteCaretaker(@PathVariable Long id) {
        caretakerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
