package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.PlantCommonName;
import com.olympp.backend.service.PlantCommonNameService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.PlantCommonNameCriteria;
import com.olympp.backend.service.PlantCommonNameQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PlantCommonName.
 */
@RestController
@RequestMapping("/api")
public class PlantCommonNameResource {

    private final Logger log = LoggerFactory.getLogger(PlantCommonNameResource.class);

    private static final String ENTITY_NAME = "backendPlantCommonName";

    private final PlantCommonNameService plantCommonNameService;

    private final PlantCommonNameQueryService plantCommonNameQueryService;

    public PlantCommonNameResource(PlantCommonNameService plantCommonNameService, PlantCommonNameQueryService plantCommonNameQueryService) {
        this.plantCommonNameService = plantCommonNameService;
        this.plantCommonNameQueryService = plantCommonNameQueryService;
    }

    /**
     * POST  /plant-common-names : Create a new plantCommonName.
     *
     * @param plantCommonName the plantCommonName to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantCommonName, or with status 400 (Bad Request) if the plantCommonName has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plant-common-names")
    @Timed
    public ResponseEntity<PlantCommonName> createPlantCommonName(@RequestBody PlantCommonName plantCommonName) throws URISyntaxException {
        log.debug("REST request to save PlantCommonName : {}", plantCommonName);
        if (plantCommonName.getId() != null) {
            throw new BadRequestAlertException("A new plantCommonName cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantCommonName result = plantCommonNameService.save(plantCommonName);
        return ResponseEntity.created(new URI("/api/plant-common-names/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plant-common-names : Updates an existing plantCommonName.
     *
     * @param plantCommonName the plantCommonName to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantCommonName,
     * or with status 400 (Bad Request) if the plantCommonName is not valid,
     * or with status 500 (Internal Server Error) if the plantCommonName couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plant-common-names")
    @Timed
    public ResponseEntity<PlantCommonName> updatePlantCommonName(@RequestBody PlantCommonName plantCommonName) throws URISyntaxException {
        log.debug("REST request to update PlantCommonName : {}", plantCommonName);
        if (plantCommonName.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantCommonName result = plantCommonNameService.save(plantCommonName);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantCommonName.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plant-common-names : get all the plantCommonNames.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of plantCommonNames in body
     */
    @GetMapping("/plant-common-names")
    @Timed
    public ResponseEntity<List<PlantCommonName>> getAllPlantCommonNames(PlantCommonNameCriteria criteria) {
        log.debug("REST request to get PlantCommonNames by criteria: {}", criteria);
        List<PlantCommonName> entityList = plantCommonNameQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /plant-common-names/count : count all the plantCommonNames.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plant-common-names/count")
    @Timed
    public ResponseEntity<Long> countPlantCommonNames(PlantCommonNameCriteria criteria) {
        log.debug("REST request to count PlantCommonNames by criteria: {}", criteria);
        return ResponseEntity.ok().body(plantCommonNameQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plant-common-names/:id : get the "id" plantCommonName.
     *
     * @param id the id of the plantCommonName to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantCommonName, or with status 404 (Not Found)
     */
    @GetMapping("/plant-common-names/{id}")
    @Timed
    public ResponseEntity<PlantCommonName> getPlantCommonName(@PathVariable Long id) {
        log.debug("REST request to get PlantCommonName : {}", id);
        Optional<PlantCommonName> plantCommonName = plantCommonNameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(plantCommonName);
    }

    /**
     * DELETE  /plant-common-names/:id : delete the "id" plantCommonName.
     *
     * @param id the id of the plantCommonName to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plant-common-names/{id}")
    @Timed
    public ResponseEntity<Void> deletePlantCommonName(@PathVariable Long id) {
        log.debug("REST request to delete PlantCommonName : {}", id);
        plantCommonNameService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
