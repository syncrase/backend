package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.Ordre;
import com.olympp.backend.service.OrdreService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.OrdreCriteria;
import com.olympp.backend.service.OrdreQueryService;
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
 * REST controller for managing Ordre.
 */
@RestController
@RequestMapping("/api")
public class OrdreResource {

    private final Logger log = LoggerFactory.getLogger(OrdreResource.class);

    private static final String ENTITY_NAME = "backendOrdre";

    private final OrdreService ordreService;

    private final OrdreQueryService ordreQueryService;

    public OrdreResource(OrdreService ordreService, OrdreQueryService ordreQueryService) {
        this.ordreService = ordreService;
        this.ordreQueryService = ordreQueryService;
    }

    /**
     * POST  /ordres : Create a new ordre.
     *
     * @param ordre the ordre to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ordre, or with status 400 (Bad Request) if the ordre has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ordres")
    @Timed
    public ResponseEntity<Ordre> createOrdre(@RequestBody Ordre ordre) throws URISyntaxException {
        log.debug("REST request to save Ordre : {}", ordre);
        if (ordre.getId() != null) {
            throw new BadRequestAlertException("A new ordre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ordre result = ordreService.save(ordre);
        return ResponseEntity.created(new URI("/api/ordres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ordres : Updates an existing ordre.
     *
     * @param ordre the ordre to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ordre,
     * or with status 400 (Bad Request) if the ordre is not valid,
     * or with status 500 (Internal Server Error) if the ordre couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ordres")
    @Timed
    public ResponseEntity<Ordre> updateOrdre(@RequestBody Ordre ordre) throws URISyntaxException {
        log.debug("REST request to update Ordre : {}", ordre);
        if (ordre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Ordre result = ordreService.save(ordre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ordre.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ordres : get all the ordres.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of ordres in body
     */
    @GetMapping("/ordres")
    @Timed
    public ResponseEntity<List<Ordre>> getAllOrdres(OrdreCriteria criteria) {
        log.debug("REST request to get Ordres by criteria: {}", criteria);
        List<Ordre> entityList = ordreQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /ordres/count : count all the ordres.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/ordres/count")
    @Timed
    public ResponseEntity<Long> countOrdres(OrdreCriteria criteria) {
        log.debug("REST request to count Ordres by criteria: {}", criteria);
        return ResponseEntity.ok().body(ordreQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /ordres/:id : get the "id" ordre.
     *
     * @param id the id of the ordre to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ordre, or with status 404 (Not Found)
     */
    @GetMapping("/ordres/{id}")
    @Timed
    public ResponseEntity<Ordre> getOrdre(@PathVariable Long id) {
        log.debug("REST request to get Ordre : {}", id);
        Optional<Ordre> ordre = ordreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ordre);
    }

    /**
     * DELETE  /ordres/:id : delete the "id" ordre.
     *
     * @param id the id of the ordre to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ordres/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrdre(@PathVariable Long id) {
        log.debug("REST request to delete Ordre : {}", id);
        ordreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
