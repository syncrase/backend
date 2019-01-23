package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.Floraison;
import com.olympp.backend.service.FloraisonService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.FloraisonCriteria;
import com.olympp.backend.service.FloraisonQueryService;
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
 * REST controller for managing Floraison.
 */
@RestController
@RequestMapping("/api")
public class FloraisonResource {

    private final Logger log = LoggerFactory.getLogger(FloraisonResource.class);

    private static final String ENTITY_NAME = "backendFloraison";

    private final FloraisonService floraisonService;

    private final FloraisonQueryService floraisonQueryService;

    public FloraisonResource(FloraisonService floraisonService, FloraisonQueryService floraisonQueryService) {
        this.floraisonService = floraisonService;
        this.floraisonQueryService = floraisonQueryService;
    }

    /**
     * POST  /floraisons : Create a new floraison.
     *
     * @param floraison the floraison to create
     * @return the ResponseEntity with status 201 (Created) and with body the new floraison, or with status 400 (Bad Request) if the floraison has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/floraisons")
    @Timed
    public ResponseEntity<Floraison> createFloraison(@RequestBody Floraison floraison) throws URISyntaxException {
        log.debug("REST request to save Floraison : {}", floraison);
        if (floraison.getId() != null) {
            throw new BadRequestAlertException("A new floraison cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Floraison result = floraisonService.save(floraison);
        return ResponseEntity.created(new URI("/api/floraisons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /floraisons : Updates an existing floraison.
     *
     * @param floraison the floraison to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated floraison,
     * or with status 400 (Bad Request) if the floraison is not valid,
     * or with status 500 (Internal Server Error) if the floraison couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/floraisons")
    @Timed
    public ResponseEntity<Floraison> updateFloraison(@RequestBody Floraison floraison) throws URISyntaxException {
        log.debug("REST request to update Floraison : {}", floraison);
        if (floraison.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Floraison result = floraisonService.save(floraison);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, floraison.getId().toString()))
            .body(result);
    }

    /**
     * GET  /floraisons : get all the floraisons.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of floraisons in body
     */
    @GetMapping("/floraisons")
    @Timed
    public ResponseEntity<List<Floraison>> getAllFloraisons(FloraisonCriteria criteria) {
        log.debug("REST request to get Floraisons by criteria: {}", criteria);
        List<Floraison> entityList = floraisonQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /floraisons/count : count all the floraisons.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/floraisons/count")
    @Timed
    public ResponseEntity<Long> countFloraisons(FloraisonCriteria criteria) {
        log.debug("REST request to count Floraisons by criteria: {}", criteria);
        return ResponseEntity.ok().body(floraisonQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /floraisons/:id : get the "id" floraison.
     *
     * @param id the id of the floraison to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the floraison, or with status 404 (Not Found)
     */
    @GetMapping("/floraisons/{id}")
    @Timed
    public ResponseEntity<Floraison> getFloraison(@PathVariable Long id) {
        log.debug("REST request to get Floraison : {}", id);
        Optional<Floraison> floraison = floraisonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(floraison);
    }

    /**
     * DELETE  /floraisons/:id : delete the "id" floraison.
     *
     * @param id the id of the floraison to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/floraisons/{id}")
    @Timed
    public ResponseEntity<Void> deleteFloraison(@PathVariable Long id) {
        log.debug("REST request to delete Floraison : {}", id);
        floraisonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
