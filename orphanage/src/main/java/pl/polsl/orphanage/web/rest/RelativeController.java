package pl.polsl.orphanage.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.orphanage.service.RelativeService;
import pl.polsl.orphanage.service.dto.RelativeDTO;
import pl.polsl.orphanage.web.rest.errors.BadRequestAlertException;
import pl.polsl.orphanage.web.rest.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing Relative.
 */
@RestController
@RequestMapping("/api")
public class RelativeController {

    private RelativeService relativeService;
    private static final String ENTITY_NAME = "Relative";


    public RelativeController(RelativeService relativeService) {
        this.relativeService = relativeService;
    }

    /**
     * POST  /relative : Create a new relative.
     *
     * @param relativeDTO the relativeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new relativeDTO, or with status 400 (Bad Request) if the relative has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/relative")
    public ResponseEntity<RelativeDTO> createRelative(@RequestBody RelativeDTO relativeDTO) throws URISyntaxException {
        if (relativeDTO.getId() != null) {
            throw new BadRequestAlertException("A new department cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelativeDTO result = relativeService.save(relativeDTO);
        return ResponseEntity.created(new URI("/api/relative/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /relative : Updates an existing relative.
     *
     * @param relativeDTO the relativeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated relativeDTO,
     * or with status 400 (Bad Request) if the relativeDTO is not valid,
     * or with status 500 (Internal Server Error) if the relativeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/relative")
    public ResponseEntity<RelativeDTO> updateRelative(@RequestBody RelativeDTO relativeDTO) throws URISyntaxException { ;
        if (relativeDTO.getId() == null) {
            return createRelative(relativeDTO);
        }
        RelativeDTO result = relativeService.save(relativeDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, relativeDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET  /relative/:id : get relatives by fosterling`s id.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of relative in body
     */
    @GetMapping("/relative/{id}")
    public List<RelativeDTO> getAllFosterlingByCaretaker(@RequestParam Long id) {
        return relativeService.findAllByFosterling(id);
    }

    /**
     * GET  /search/relative/:lastname : get relatives by fosterling`s id.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of relative in body
     */
    @GetMapping("/search/relative/{lastname}")
    public List<RelativeDTO> getAllFosterlingByCaretaker(@RequestParam String lastname) {
        return relativeService.findAllByLastname(lastname);
    }
}
