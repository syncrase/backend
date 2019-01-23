package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.Mois;
import com.olympp.backend.service.MoisService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.MoisCriteria;
import com.olympp.backend.service.MoisQueryService;
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
 * REST controller for managing Mois.
 */
@RestController
@RequestMapping("/api")
public class MoisResource {

    private final Logger log = LoggerFactory.getLogger(MoisResource.class);

    private static final String ENTITY_NAME = "backendMois";

    private final MoisService moisService;

    private final MoisQueryService moisQueryService;

    public MoisResource(MoisService moisService, MoisQueryService moisQueryService) {
        this.moisService = moisService;
        this.moisQueryService = moisQueryService;
    }

    /**
     * POST  /mois : Create a new mois.
     *
     * @param mois the mois to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mois, or with status 400 (Bad Request) if the mois has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mois")
    @Timed
    public ResponseEntity<Mois> createMois(@RequestBody Mois mois) throws URISyntaxException {
        log.debug("REST request to save Mois : {}", mois);
        if (mois.getId() != null) {
            throw new BadRequestAlertException("A new mois cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Mois result = moisService.save(mois);
        return ResponseEntity.created(new URI("/api/mois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mois : Updates an existing mois.
     *
     * @param mois the mois to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mois,
     * or with status 400 (Bad Request) if the mois is not valid,
     * or with status 500 (Internal Server Error) if the mois couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mois")
    @Timed
    public ResponseEntity<Mois> updateMois(@RequestBody Mois mois) throws URISyntaxException {
        log.debug("REST request to update Mois : {}", mois);
        if (mois.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Mois result = moisService.save(mois);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mois.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mois : get all the mois.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of mois in body
     */
    @GetMapping("/mois")
    @Timed
    public ResponseEntity<List<Mois>> getAllMois(MoisCriteria criteria) {
        log.debug("REST request to get Mois by criteria: {}", criteria);
        List<Mois> entityList = moisQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /mois/count : count all the mois.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/mois/count")
    @Timed
    public ResponseEntity<Long> countMois(MoisCriteria criteria) {
        log.debug("REST request to count Mois by criteria: {}", criteria);
        return ResponseEntity.ok().body(moisQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /mois/:id : get the "id" mois.
     *
     * @param id the id of the mois to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mois, or with status 404 (Not Found)
     */
    @GetMapping("/mois/{id}")
    @Timed
    public ResponseEntity<Mois> getMois(@PathVariable Long id) {
        log.debug("REST request to get Mois : {}", id);
        Optional<Mois> mois = moisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mois);
    }

    /**
     * DELETE  /mois/:id : delete the "id" mois.
     *
     * @param id the id of the mois to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mois/{id}")
    @Timed
    public ResponseEntity<Void> deleteMois(@PathVariable Long id) {
        log.debug("REST request to delete Mois : {}", id);
        moisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
