package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.Reference;
import com.olympp.backend.service.ReferenceService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.ReferenceCriteria;
import com.olympp.backend.service.ReferenceQueryService;
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
 * REST controller for managing Reference.
 */
@RestController
@RequestMapping("/api")
public class ReferenceResource {

    private final Logger log = LoggerFactory.getLogger(ReferenceResource.class);

    private static final String ENTITY_NAME = "backendReference";

    private final ReferenceService referenceService;

    private final ReferenceQueryService referenceQueryService;

    public ReferenceResource(ReferenceService referenceService, ReferenceQueryService referenceQueryService) {
        this.referenceService = referenceService;
        this.referenceQueryService = referenceQueryService;
    }

    /**
     * POST  /references : Create a new reference.
     *
     * @param reference the reference to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reference, or with status 400 (Bad Request) if the reference has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/references")
    @Timed
    public ResponseEntity<Reference> createReference(@RequestBody Reference reference) throws URISyntaxException {
        log.debug("REST request to save Reference : {}", reference);
        if (reference.getId() != null) {
            throw new BadRequestAlertException("A new reference cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Reference result = referenceService.save(reference);
        return ResponseEntity.created(new URI("/api/references/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /references : Updates an existing reference.
     *
     * @param reference the reference to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reference,
     * or with status 400 (Bad Request) if the reference is not valid,
     * or with status 500 (Internal Server Error) if the reference couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/references")
    @Timed
    public ResponseEntity<Reference> updateReference(@RequestBody Reference reference) throws URISyntaxException {
        log.debug("REST request to update Reference : {}", reference);
        if (reference.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Reference result = referenceService.save(reference);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reference.getId().toString()))
            .body(result);
    }

    /**
     * GET  /references : get all the references.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of references in body
     */
    @GetMapping("/references")
    @Timed
    public ResponseEntity<List<Reference>> getAllReferences(ReferenceCriteria criteria) {
        log.debug("REST request to get References by criteria: {}", criteria);
        List<Reference> entityList = referenceQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /references/count : count all the references.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/references/count")
    @Timed
    public ResponseEntity<Long> countReferences(ReferenceCriteria criteria) {
        log.debug("REST request to count References by criteria: {}", criteria);
        return ResponseEntity.ok().body(referenceQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /references/:id : get the "id" reference.
     *
     * @param id the id of the reference to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reference, or with status 404 (Not Found)
     */
    @GetMapping("/references/{id}")
    @Timed
    public ResponseEntity<Reference> getReference(@PathVariable Long id) {
        log.debug("REST request to get Reference : {}", id);
        Optional<Reference> reference = referenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reference);
    }

    /**
     * DELETE  /references/:id : delete the "id" reference.
     *
     * @param id the id of the reference to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/references/{id}")
    @Timed
    public ResponseEntity<Void> deleteReference(@PathVariable Long id) {
        log.debug("REST request to delete Reference : {}", id);
        referenceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
