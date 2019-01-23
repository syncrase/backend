package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.VitesseCroissance;
import com.olympp.backend.service.VitesseCroissanceService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.VitesseCroissanceCriteria;
import com.olympp.backend.service.VitesseCroissanceQueryService;
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
 * REST controller for managing VitesseCroissance.
 */
@RestController
@RequestMapping("/api")
public class VitesseCroissanceResource {

    private final Logger log = LoggerFactory.getLogger(VitesseCroissanceResource.class);

    private static final String ENTITY_NAME = "backendVitesseCroissance";

    private final VitesseCroissanceService vitesseCroissanceService;

    private final VitesseCroissanceQueryService vitesseCroissanceQueryService;

    public VitesseCroissanceResource(VitesseCroissanceService vitesseCroissanceService, VitesseCroissanceQueryService vitesseCroissanceQueryService) {
        this.vitesseCroissanceService = vitesseCroissanceService;
        this.vitesseCroissanceQueryService = vitesseCroissanceQueryService;
    }

    /**
     * POST  /vitesse-croissances : Create a new vitesseCroissance.
     *
     * @param vitesseCroissance the vitesseCroissance to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vitesseCroissance, or with status 400 (Bad Request) if the vitesseCroissance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vitesse-croissances")
    @Timed
    public ResponseEntity<VitesseCroissance> createVitesseCroissance(@RequestBody VitesseCroissance vitesseCroissance) throws URISyntaxException {
        log.debug("REST request to save VitesseCroissance : {}", vitesseCroissance);
        if (vitesseCroissance.getId() != null) {
            throw new BadRequestAlertException("A new vitesseCroissance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VitesseCroissance result = vitesseCroissanceService.save(vitesseCroissance);
        return ResponseEntity.created(new URI("/api/vitesse-croissances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vitesse-croissances : Updates an existing vitesseCroissance.
     *
     * @param vitesseCroissance the vitesseCroissance to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vitesseCroissance,
     * or with status 400 (Bad Request) if the vitesseCroissance is not valid,
     * or with status 500 (Internal Server Error) if the vitesseCroissance couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vitesse-croissances")
    @Timed
    public ResponseEntity<VitesseCroissance> updateVitesseCroissance(@RequestBody VitesseCroissance vitesseCroissance) throws URISyntaxException {
        log.debug("REST request to update VitesseCroissance : {}", vitesseCroissance);
        if (vitesseCroissance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VitesseCroissance result = vitesseCroissanceService.save(vitesseCroissance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vitesseCroissance.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vitesse-croissances : get all the vitesseCroissances.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of vitesseCroissances in body
     */
    @GetMapping("/vitesse-croissances")
    @Timed
    public ResponseEntity<List<VitesseCroissance>> getAllVitesseCroissances(VitesseCroissanceCriteria criteria) {
        log.debug("REST request to get VitesseCroissances by criteria: {}", criteria);
        List<VitesseCroissance> entityList = vitesseCroissanceQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /vitesse-croissances/count : count all the vitesseCroissances.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/vitesse-croissances/count")
    @Timed
    public ResponseEntity<Long> countVitesseCroissances(VitesseCroissanceCriteria criteria) {
        log.debug("REST request to count VitesseCroissances by criteria: {}", criteria);
        return ResponseEntity.ok().body(vitesseCroissanceQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /vitesse-croissances/:id : get the "id" vitesseCroissance.
     *
     * @param id the id of the vitesseCroissance to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vitesseCroissance, or with status 404 (Not Found)
     */
    @GetMapping("/vitesse-croissances/{id}")
    @Timed
    public ResponseEntity<VitesseCroissance> getVitesseCroissance(@PathVariable Long id) {
        log.debug("REST request to get VitesseCroissance : {}", id);
        Optional<VitesseCroissance> vitesseCroissance = vitesseCroissanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vitesseCroissance);
    }

    /**
     * DELETE  /vitesse-croissances/:id : delete the "id" vitesseCroissance.
     *
     * @param id the id of the vitesseCroissance to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vitesse-croissances/{id}")
    @Timed
    public ResponseEntity<Void> deleteVitesseCroissance(@PathVariable Long id) {
        log.debug("REST request to delete VitesseCroissance : {}", id);
        vitesseCroissanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
