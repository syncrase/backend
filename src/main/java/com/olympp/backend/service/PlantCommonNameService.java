package com.olympp.backend.service;

import com.olympp.backend.domain.PlantCommonName;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PlantCommonName.
 */
public interface PlantCommonNameService {

    /**
     * Save a plantCommonName.
     *
     * @param plantCommonName the entity to save
     * @return the persisted entity
     */
    PlantCommonName save(PlantCommonName plantCommonName);

    /**
     * Get all the plantCommonNames.
     *
     * @return the list of entities
     */
    List<PlantCommonName> findAll();


    /**
     * Get the "id" plantCommonName.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlantCommonName> findOne(Long id);

    /**
     * Delete the "id" plantCommonName.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
