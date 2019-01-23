package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.TypeFeuillage;
import com.olympp.backend.service.TypeFeuillageService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.TypeFeuillageCriteria;
import com.olympp.backend.service.TypeFeuillageQueryService;
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
 * REST controller for managing TypeFeuillage.
 */
@RestController
@RequestMapping("/api")
public class TypeFeuillageResource {

    private final Logger log = LoggerFactory.getLogger(TypeFeuillageResource.class);

    private static final String ENTITY_NAME = "backendTypeFeuillage";

    private final TypeFeuillageService typeFeuillageService;

    private final TypeFeuillageQueryService typeFeuillageQueryService;

    public TypeFeuillageResource(TypeFeuillageService typeFeuillageService, TypeFeuillageQueryService typeFeuillageQueryService) {
        this.typeFeuillageService = typeFeuillageService;
        this.typeFeuillageQueryService = typeFeuillageQueryService;
    }

    /**
     * POST  /type-feuillages : Create a new typeFeuillage.
     *
     * @param typeFeuillage the typeFeuillage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeFeuillage, or with status 400 (Bad Request) if the typeFeuillage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-feuillages")
    @Timed
    public ResponseEntity<TypeFeuillage> createTypeFeuillage(@RequestBody TypeFeuillage typeFeuillage) throws URISyntaxException {
        log.debug("REST request to save TypeFeuillage : {}", typeFeuillage);
        if (typeFeuillage.getId() != null) {
            throw new BadRequestAlertException("A new typeFeuillage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeFeuillage result = typeFeuillageService.save(typeFeuillage);
        return ResponseEntity.created(new URI("/api/type-feuillages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-feuillages : Updates an existing typeFeuillage.
     *
     * @param typeFeuillage the typeFeuillage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeFeuillage,
     * or with status 400 (Bad Request) if the typeFeuillage is not valid,
     * or with status 500 (Internal Server Error) if the typeFeuillage couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-feuillages")
    @Timed
    public ResponseEntity<TypeFeuillage> updateTypeFeuillage(@RequestBody TypeFeuillage typeFeuillage) throws URISyntaxException {
        log.debug("REST request to update TypeFeuillage : {}", typeFeuillage);
        if (typeFeuillage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeFeuillage result = typeFeuillageService.save(typeFeuillage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeFeuillage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-feuillages : get all the typeFeuillages.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of typeFeuillages in body
     */
    @GetMapping("/type-feuillages")
    @Timed
    public ResponseEntity<List<TypeFeuillage>> getAllTypeFeuillages(TypeFeuillageCriteria criteria) {
        log.debug("REST request to get TypeFeuillages by criteria: {}", criteria);
        List<TypeFeuillage> entityList = typeFeuillageQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /type-feuillages/count : count all the typeFeuillages.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/type-feuillages/count")
    @Timed
    public ResponseEntity<Long> countTypeFeuillages(TypeFeuillageCriteria criteria) {
        log.debug("REST request to count TypeFeuillages by criteria: {}", criteria);
        return ResponseEntity.ok().body(typeFeuillageQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /type-feuillages/:id : get the "id" typeFeuillage.
     *
     * @param id the id of the typeFeuillage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeFeuillage, or with status 404 (Not Found)
     */
    @GetMapping("/type-feuillages/{id}")
    @Timed
    public ResponseEntity<TypeFeuillage> getTypeFeuillage(@PathVariable Long id) {
        log.debug("REST request to get TypeFeuillage : {}", id);
        Optional<TypeFeuillage> typeFeuillage = typeFeuillageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeFeuillage);
    }

    /**
     * DELETE  /type-feuillages/:id : delete the "id" typeFeuillage.
     *
     * @param id the id of the typeFeuillage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-feuillages/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeFeuillage(@PathVariable Long id) {
        log.debug("REST request to delete TypeFeuillage : {}", id);
        typeFeuillageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
