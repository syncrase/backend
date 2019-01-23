package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.Famille;
import com.olympp.backend.service.FamilleService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.FamilleCriteria;
import com.olympp.backend.service.FamilleQueryService;
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
 * REST controller for managing Famille.
 */
@RestController
@RequestMapping("/api")
public class FamilleResource {

    private final Logger log = LoggerFactory.getLogger(FamilleResource.class);

    private static final String ENTITY_NAME = "backendFamille";

    private final FamilleService familleService;

    private final FamilleQueryService familleQueryService;

    public FamilleResource(FamilleService familleService, FamilleQueryService familleQueryService) {
        this.familleService = familleService;
        this.familleQueryService = familleQueryService;
    }

    /**
     * POST  /familles : Create a new famille.
     *
     * @param famille the famille to create
     * @return the ResponseEntity with status 201 (Created) and with body the new famille, or with status 400 (Bad Request) if the famille has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/familles")
    @Timed
    public ResponseEntity<Famille> createFamille(@RequestBody Famille famille) throws URISyntaxException {
        log.debug("REST request to save Famille : {}", famille);
        if (famille.getId() != null) {
            throw new BadRequestAlertException("A new famille cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Famille result = familleService.save(famille);
        return ResponseEntity.created(new URI("/api/familles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /familles : Updates an existing famille.
     *
     * @param famille the famille to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated famille,
     * or with status 400 (Bad Request) if the famille is not valid,
     * or with status 500 (Internal Server Error) if the famille couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/familles")
    @Timed
    public ResponseEntity<Famille> updateFamille(@RequestBody Famille famille) throws URISyntaxException {
        log.debug("REST request to update Famille : {}", famille);
        if (famille.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Famille result = familleService.save(famille);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, famille.getId().toString()))
            .body(result);
    }

    /**
     * GET  /familles : get all the familles.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of familles in body
     */
    @GetMapping("/familles")
    @Timed
    public ResponseEntity<List<Famille>> getAllFamilles(FamilleCriteria criteria) {
        log.debug("REST request to get Familles by criteria: {}", criteria);
        List<Famille> entityList = familleQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /familles/count : count all the familles.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/familles/count")
    @Timed
    public ResponseEntity<Long> countFamilles(FamilleCriteria criteria) {
        log.debug("REST request to count Familles by criteria: {}", criteria);
        return ResponseEntity.ok().body(familleQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /familles/:id : get the "id" famille.
     *
     * @param id the id of the famille to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the famille, or with status 404 (Not Found)
     */
    @GetMapping("/familles/{id}")
    @Timed
    public ResponseEntity<Famille> getFamille(@PathVariable Long id) {
        log.debug("REST request to get Famille : {}", id);
        Optional<Famille> famille = familleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(famille);
    }

    /**
     * DELETE  /familles/:id : delete the "id" famille.
     *
     * @param id the id of the famille to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/familles/{id}")
    @Timed
    public ResponseEntity<Void> deleteFamille(@PathVariable Long id) {
        log.debug("REST request to delete Famille : {}", id);
        familleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
