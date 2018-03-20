package pl.polsl.orphanage.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.orphanage.domain.Reward;
import pl.polsl.orphanage.repository.RewardRepository;
import pl.polsl.orphanage.service.dto.RewardDTO;
import pl.polsl.orphanage.service.mapper.RewardMapper;
import pl.polsl.orphanage.web.rest.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing Reward.
 */
public class RewardController {

    private static final String ENTITY_NAME = "Reward";

    private RewardRepository rewardRepository;

    private RewardMapper rewardMapper;

    public RewardController(RewardRepository rewardRepository, RewardMapper rewardMapper) {
        this.rewardRepository = rewardRepository;
        this.rewardMapper = rewardMapper;
    }

    /**
     * POST  /rewards : Create a new reward.
     *
     * @param rewardDTO the rewardDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rewardDTO, or with status 400 (Bad Request) if the reward has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rewards")
    public ResponseEntity<RewardDTO> createEmployee(@RequestBody RewardDTO rewardDTO) throws URISyntaxException {

        Reward reward = rewardMapper.toEntity(rewardDTO);
        reward = rewardRepository.save(reward);
        RewardDTO result = rewardMapper.toDto(reward);
        return ResponseEntity.created(new URI("/api/employees/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /rewards : Updates an existing document.
     *
     * @param rewardDTO the rewardDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rewardDTO,
     * or with status 400 (Bad Request) if the rewardDTO is not valid,
     * or with status 500 (Internal Server Error) if the rewardDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rewards")
    public ResponseEntity<RewardDTO> updateEmployee(@RequestBody RewardDTO rewardDTO) throws URISyntaxException {
        if (rewardDTO.getId() == null) {
            return createEmployee(rewardDTO);
        }
        Reward reward = rewardMapper.toEntity(rewardDTO);
        reward = rewardRepository.save(reward);
        RewardDTO result = rewardMapper.toDto(reward);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rewardDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET  /rewards : get all the employees.
     *
     * @param fosterlingId the id of the fosterling parent
     * @return the ResponseEntity with status 200 (OK) and the list of employees in body
     */
    @GetMapping("/rewards/{fosterlingId}")
    public ResponseEntity<List<RewardDTO>> getAllEmployees(@PathVariable Long fosterlingId) {
        List<Reward> list = rewardRepository.findByFosterlingId(fosterlingId);
        return new ResponseEntity<>(rewardMapper.toDto(list), HttpStatus.OK);
    }

    /**
     * DELETE  /rewards/:id : delete the "id" reward.
     *
     * @param id the id of the rewardDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rewards/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        rewardRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
