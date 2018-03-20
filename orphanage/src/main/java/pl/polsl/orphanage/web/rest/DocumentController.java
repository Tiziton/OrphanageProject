package pl.polsl.orphanage.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.orphanage.domain.Document;
import pl.polsl.orphanage.repository.DocumentRepository;
import pl.polsl.orphanage.service.dto.DocumentDTO;
import pl.polsl.orphanage.service.mapper.DocumentMapper;
import pl.polsl.orphanage.web.rest.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing Document.
 */
@RestController
@RequestMapping("/api")
public class DocumentController {

    private static final String ENTITY_NAME = "Document";

    private DocumentRepository documentRepository;

    private DocumentMapper documentMapper;

    public DocumentController(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    /**
     * POST  /documents : Create a new document.
     *
     * @param documentDTO the documentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new documentDTO, or with status 400 (Bad Request) if the document has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/documents")
    public ResponseEntity<DocumentDTO> createEmployee(@RequestBody DocumentDTO documentDTO) throws URISyntaxException {

        Document document = documentMapper.toEntity(documentDTO);
        document = documentRepository.save(document);
        DocumentDTO result = documentMapper.toDto(document);
        return ResponseEntity.created(new URI("/api/employees/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /documents : Updates an existing document.
     *
     * @param documentDTO the documentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated documentDTO,
     * or with status 400 (Bad Request) if the documentDTO is not valid,
     * or with status 500 (Internal Server Error) if the documentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/documents")
    public ResponseEntity<DocumentDTO> updateEmployee(@RequestBody DocumentDTO documentDTO) throws URISyntaxException {
        if (documentDTO.getId() == null) {
            return createEmployee(documentDTO);
        }
        Document employee = documentMapper.toEntity(documentDTO);
        employee = documentRepository.save(employee);
        DocumentDTO result = documentMapper.toDto(employee);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, documentDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET  /employees/:fosterlingId : get all the employees.
     *
     * @param fosterlingId the id of the fosterling parent
     * @return the ResponseEntity with status 200 (OK) and the list of employees in body
     */
    @GetMapping("/documents/{fosterlingId}")
    public ResponseEntity<List<DocumentDTO>> getAllEmployees(@PathVariable Long fosterlingId) {
        List<Document> page = documentRepository.findByFosterlingId(fosterlingId);
        return new ResponseEntity<>(documentMapper.toDto(page),HttpStatus.OK);
    }

    /**
     * DELETE  /documents/:id : delete the "id" document.
     *
     * @param id the id of the documentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        documentRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
