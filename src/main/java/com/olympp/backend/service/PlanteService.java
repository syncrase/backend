package com.olympp.backend.service;

import com.olympp.backend.domain.Plante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Plante.
 */
public interface PlanteService {

    /**
     * Save a plante.
     *
     * @param plante the entity to save
     * @return the persisted entity
     */
    Plante save(Plante plante);

    /**
     * Get all the plantes.
     *
     * @return the list of entities
     */
    List<Plante> findAll();

    /**
     * Get all the Plante with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Plante> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" plante.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Plante> findOne(Long id);

    /**
     * Delete the "id" plante.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
