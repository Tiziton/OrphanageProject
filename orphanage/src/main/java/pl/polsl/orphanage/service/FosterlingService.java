package pl.polsl.orphanage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.orphanage.domain.Fosterling;
import pl.polsl.orphanage.repository.FosterlingRepository;
import pl.polsl.orphanage.service.dto.FosterlingDTO;
import pl.polsl.orphanage.service.mapper.FosterlingMapper;


import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Interface for managing Fosterling.
 */
@Service
@Transactional
public class FosterlingService {

    private FosterlingRepository fosterlingRepository;

    private FosterlingMapper fosterlingMapper;

    public FosterlingService(FosterlingRepository fosterlingRepository, FosterlingMapper fosterlingMapper) {
        this.fosterlingRepository = fosterlingRepository;
        this.fosterlingMapper = fosterlingMapper;
    }

    /**
     * Save a fosterling.
     *
     * @param fosterlingDTO the entity to save
     * @return the persisted entity
     */
    public FosterlingDTO save(FosterlingDTO fosterlingDTO){
        Fosterling fosterling = fosterlingMapper.toEntity(fosterlingDTO);
        fosterling = fosterlingRepository.save(fosterling);
        FosterlingDTO result = fosterlingMapper.toDto(fosterling);
        return result;
    }

    /**
     * Get all the fosterlings.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FosterlingDTO> findAll(){
        return fosterlingRepository.findAll().stream()
                .map(fosterlingMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get fosterling by caretaker entity.
     *
     * @param caretakerId the id of caretaker id
     * @return the list of entities
     */
    public List<FosterlingDTO> findByCaretaker(Long caretakerId){
        return fosterlingRepository.findAllByCaretaker(caretakerId).stream()
                .map(fosterlingMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    /**
     * Get fosterling by entity`s id.
     *
     * @param id the id of fosterling id
     * @return the entity
     */
    public FosterlingDTO findById(Long id){
        return fosterlingMapper.toDto(fosterlingRepository.findOneById(id));

    }

    /**
     * Search fosterling by lastname
     *
     * @param lastname the prop of fosterling entity
     * @return list of entities
     */
    public List<FosterlingDTO> findFosterlingByName(String lastname){
        return fosterlingRepository
                .findAllByLastnameIgnoreCaseContaining("%" + lastname + "%").stream()
                .map(fosterlingMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Search fosterling between birth date
     *
     * @param from the date from search
     * @param to the date to search
     * @return list of entities
     */
    public List<FosterlingDTO> findFosterlingByBirthDate(Date from, Date to){
        return fosterlingRepository
                .findAllBetweenDate(from, to).stream()
                .map(fosterlingMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Search all sibilings of fosterling, search by id
     *
     * @param id the id of fosterling entity
     * @return the list of entities
     */
    public List<FosterlingDTO> findFosterlingSibilings(Long id){
        return fosterlingRepository
                .findAllSibilings(id).stream()
                .map(fosterlingMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Add sibiling to fosterling entity
     *
     * @param id the id of fosterling entity
     * @param sibilingId the id of fosterling`s sibiling
     */
    public void addFosterlingSibiling(Long id, Long sibilingId){
        Fosterling fosterling = fosterlingRepository.findOneById(id);
        Fosterling sibiling = fosterlingRepository.findOneById(sibilingId);
        fosterling.getSibilings().add(sibiling);
        sibiling.getSibilings().add(fosterling);
        fosterlingRepository.save(fosterling);
        fosterlingRepository.save(sibiling);
    }

    /**
     * Delete the sibiling of fosterling entity
     *
     * @param id the id of fosterling entity
     * @param sibilingId the id of fosterling`s sibiling
     */
    public void deleteFosterlingSibiling(Long id, Long sibilingId) {
        Fosterling fosterling = fosterlingRepository.findOneById(id);
        Fosterling sibiling = fosterlingRepository.findOneById(sibilingId);
        fosterling.getSibilings().remove(sibiling);
        sibiling.getSibilings().remove(fosterling);
        fosterlingRepository.save(fosterling);
        fosterlingRepository.save(sibiling);
    }

    /**
     * Delete the "id" fosterling.
     *
     * @param id the id of the entity
     */
    public void delete(Long id){

        Fosterling fosterling = fosterlingRepository.findOneById(id);

        fosterling
                .setRelatives(new HashSet<>());

        fosterling
                .setSibilings(new HashSet<>());

        fosterlingRepository.save(fosterling);

        fosterlingRepository.deleteById(id);
    }
}
