package pl.polsl.orphanage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.orphanage.domain.Caretaker;
import pl.polsl.orphanage.repository.CaretakerRepository;
import pl.polsl.orphanage.service.dto.CaretakerDTO;
import pl.polsl.orphanage.service.mapper.CaretakerMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Interface for managing Caretaker.
 */
@Service
@Transactional
public class CaretakerService {

    private CaretakerRepository caretakerRepository;

    private CaretakerMapper caretakerMapper;

    public CaretakerService(CaretakerRepository caretakerRepository, CaretakerMapper caretakerMapper) {
        this.caretakerRepository = caretakerRepository;
        this.caretakerMapper = caretakerMapper;
    }

    /**
     * Save a caretaker.
     *
     * @param caretakerDTO the entity to save
     * @return the persisted entity
     */
    public CaretakerDTO save(CaretakerDTO caretakerDTO){
        Caretaker caretaker = caretakerMapper.toEntity(caretakerDTO);
        caretaker = caretakerRepository.save(caretaker);
        CaretakerDTO result = caretakerMapper.toDto(caretaker);
        return result;
    }

    /**
     * Get all the caretakers.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    List<CaretakerDTO> findAll(){
        return caretakerRepository.findAll().stream()
                .map(caretakerMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get actual logged caretaker.
     *
     * @param userId the id of user entity
     * @return the entity
     */
    public CaretakerDTO findCaretakerByUserId(Long userId){
        return caretakerMapper.toDto(caretakerRepository.findByUserId(userId));
    }

    /**
     * Delete the "id" caretaker.
     *
     * @param id the id of the entity
     */
    public void delete(Long id){
        caretakerRepository.deleteById(id);
    }

}
