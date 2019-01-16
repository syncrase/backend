package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.Ensoleillement;
import com.olympp.backend.service.EnsoleillementService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
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
 * REST controller for managing Ensoleillement.
 */
@RestController
@RequestMapping("/api")
public class EnsoleillementResource {

    private final Logger log = LoggerFactory.getLogger(EnsoleillementResource.class);

    private static final String ENTITY_NAME = "backendEnsoleillement";

    private final EnsoleillementService ensoleillementService;

    public EnsoleillementResource(EnsoleillementService ensoleillementService) {
        this.ensoleillementService = ensoleillementService;
    }

    /**
     * POST  /ensoleillements : Create a new ensoleillement.
     *
     * @param ensoleillement the ensoleillement to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ensoleillement, or with status 400 (Bad Request) if the ensoleillement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ensoleillements")
    @Timed
    public ResponseEntity<Ensoleillement> createEnsoleillement(@RequestBody Ensoleillement ensoleillement) throws URISyntaxException {
        log.debug("REST request to save Ensoleillement : {}", ensoleillement);
        if (ensoleillement.getId() != null) {
            throw new BadRequestAlertException("A new ensoleillement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<Ensoleillement> fetchedEnsoleillement = ensoleillementService.findOneByEnsoleillement(ensoleillement.getEnsoleillement());
        if (fetchedEnsoleillement.isPresent() == true) {
            throw new BadRequestAlertException("An ensoleillement already have this name", ENTITY_NAME, "nameexists");
        }
        
        Ensoleillement result = ensoleillementService.save(ensoleillement);
        return ResponseEntity.created(new URI("/api/ensoleillements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ensoleillements : Updates an existing ensoleillement.
     *
     * @param ensoleillement the ensoleillement to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ensoleillement,
     * or with status 400 (Bad Request) if the ensoleillement is not valid,
     * or with status 500 (Internal Server Error) if the ensoleillement couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ensoleillements")
    @Timed
    public ResponseEntity<Ensoleillement> updateEnsoleillement(@RequestBody Ensoleillement ensoleillement) throws URISyntaxException {
        log.debug("REST request to update Ensoleillement : {}", ensoleillement);
        if (ensoleillement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Ensoleillement result = ensoleillementService.save(ensoleillement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ensoleillement.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ensoleillements : get all the ensoleillements.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ensoleillements in body
     */
    @GetMapping("/ensoleillements")
    @Timed
    public List<Ensoleillement> getAllEnsoleillements() {
        log.debug("REST request to get all Ensoleillements");
        return ensoleillementService.findAll();
    }

    /**
     * GET  /ensoleillements/:id : get the "id" ensoleillement.
     *
     * @param id the id of the ensoleillement to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ensoleillement, or with status 404 (Not Found)
     */
    @GetMapping("/ensoleillements/{id}")
    @Timed
    public ResponseEntity<Ensoleillement> getEnsoleillement(@PathVariable Long id) {
        log.debug("REST request to get Ensoleillement : {}", id);
        Optional<Ensoleillement> ensoleillement = ensoleillementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ensoleillement);
    }

    /**
     * DELETE  /ensoleillements/:id : delete the "id" ensoleillement.
     *
     * @param id the id of the ensoleillement to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ensoleillements/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnsoleillement(@PathVariable Long id) {
        log.debug("REST request to delete Ensoleillement : {}", id);
        ensoleillementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
