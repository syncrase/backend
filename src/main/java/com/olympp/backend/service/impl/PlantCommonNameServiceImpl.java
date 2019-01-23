package com.olympp.backend.service.impl;

import com.olympp.backend.service.PlantCommonNameService;
import com.olympp.backend.domain.PlantCommonName;
import com.olympp.backend.repository.PlantCommonNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing PlantCommonName.
 */
@Service
@Transactional
public class PlantCommonNameServiceImpl implements PlantCommonNameService {

    private final Logger log = LoggerFactory.getLogger(PlantCommonNameServiceImpl.class);

    private final PlantCommonNameRepository plantCommonNameRepository;

    public PlantCommonNameServiceImpl(PlantCommonNameRepository plantCommonNameRepository) {
        this.plantCommonNameRepository = plantCommonNameRepository;
    }

    /**
     * Save a plantCommonName.
     *
     * @param plantCommonName the entity to save
     * @return the persisted entity
     */
    @Override
    public PlantCommonName save(PlantCommonName plantCommonName) {
        log.debug("Request to save PlantCommonName : {}", plantCommonName);
        return plantCommonNameRepository.save(plantCommonName);
    }

    /**
     * Get all the plantCommonNames.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PlantCommonName> findAll() {
        log.debug("Request to get all PlantCommonNames");
        return plantCommonNameRepository.findAll();
    }


    /**
     * Get one plantCommonName by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlantCommonName> findOne(Long id) {
        log.debug("Request to get PlantCommonName : {}", id);
        return plantCommonNameRepository.findById(id);
    }

    /**
     * Delete the plantCommonName by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlantCommonName : {}", id);
        plantCommonNameRepository.deleteById(id);
    }
}
