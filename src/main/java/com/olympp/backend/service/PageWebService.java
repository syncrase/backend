package com.olympp.backend.service;

import com.olympp.backend.domain.PageWeb;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing PageWeb.
 */
public interface PageWebService {

    /**
     * Save a pageWeb.
     *
     * @param pageWeb the entity to save
     * @return the persisted entity
     */
    PageWeb save(PageWeb pageWeb);

    /**
     * Get all the pageWebs.
     *
     * @return the list of entities
     */
    List<PageWeb> findAll();
    /**
     * Get all the PageWebDTO where Reference is null.
     *
     * @return the list of entities
     */
    List<PageWeb> findAllWhereReferenceIsNull();


    /**
     * Get the "id" pageWeb.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PageWeb> findOne(Long id);

    /**
     * Delete the "id" pageWeb.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
