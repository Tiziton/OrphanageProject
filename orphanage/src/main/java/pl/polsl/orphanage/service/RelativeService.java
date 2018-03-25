package pl.polsl.orphanage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.orphanage.domain.Fosterling;
import pl.polsl.orphanage.domain.Relative;
import pl.polsl.orphanage.repository.FosterlingRepository;
import pl.polsl.orphanage.repository.RelativeRepository;
import pl.polsl.orphanage.service.dto.RelativeDTO;
import pl.polsl.orphanage.service.mapper.RelativeMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Interface for managing Relative.
 */
@Service
@Transactional
public class RelativeService {

    private RelativeRepository relativeRepository;

    private RelativeMapper relativeMapper;

    private FosterlingRepository fosterlingRepository;

    public RelativeService(
            RelativeRepository relativeRepository,
            RelativeMapper relativeMapper,
            FosterlingRepository fosterlingRepository) {
        this.relativeRepository = relativeRepository;
        this.relativeMapper = relativeMapper;
        this.fosterlingRepository = fosterlingRepository;
    }

    /**
     * Save a relative.
     *
     * @param relativeDTO the entity to save
     * @return the persisted entity
     */
    public RelativeDTO save(RelativeDTO relativeDTO){
        Relative relative = relativeMapper.toEntity(relativeDTO);
        relative = relativeRepository.save(relative);
        RelativeDTO result = relativeMapper.toDto(relative);
        return result;
    }

    /**
     * Get all the relatives by fosterling entity.
     *
     * @param fosterlingId the id of fosterling entity
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<RelativeDTO> findAllByFosterling(Long fosterlingId){
        return relativeRepository.findAllByFosterling(fosterlingId).stream()
                .map(relativeMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Search relatives by lastname
     *
     * @param lastname the lastname prop of relative entity
     * @return list of entity
     */
    public List<RelativeDTO> findAllByLastname(String lastname){
        return relativeRepository.findAllByLastname("%"+lastname+"%").stream()
                .map(relativeMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Delete the "id" relative.
     *
     * @param id the id of the entity
     */
    public boolean delete(Long id){

        Optional<Relative> relative = relativeRepository.findById(id);
        if(relative.isPresent()) {

            List<Fosterling> fosterlings = fosterlingRepository.findAllWithRelative(id);

            fosterlings
                    .stream()
                    .forEach(
                            (x) -> x.getRelatives().remove(relative.get())
                    );

            fosterlingRepository.saveAll(fosterlings);

            relativeRepository.deleteById(id);

            return true;
        }
        return false;
    }

    /**
     * Add relative to fosterling by id
     *
     * @param id the id of relative id
     * @param fosterlingId the id of fosterling id
     */
    public boolean addRelativeToFosterling(Long id, Long fosterlingId){
        Optional<Fosterling> fosterling = fosterlingRepository.findById(fosterlingId);
        Optional<Relative> relative = relativeRepository.findById(id);
        if(fosterling.isPresent() && relative.isPresent()){

            fosterling.get().getRelatives().add(relative.get());
            fosterlingRepository.save(fosterling.get());
            return true;
        }

        return false;

    }
}
