package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.TypeRacine;
import com.olympp.backend.service.TypeRacineService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.TypeRacineCriteria;
import com.olympp.backend.service.TypeRacineQueryService;
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
 * REST controller for managing TypeRacine.
 */
@RestController
@RequestMapping("/api")
public class TypeRacineResource {

    private final Logger log = LoggerFactory.getLogger(TypeRacineResource.class);

    private static final String ENTITY_NAME = "backendTypeRacine";

    private final TypeRacineService typeRacineService;

    private final TypeRacineQueryService typeRacineQueryService;

    public TypeRacineResource(TypeRacineService typeRacineService, TypeRacineQueryService typeRacineQueryService) {
        this.typeRacineService = typeRacineService;
        this.typeRacineQueryService = typeRacineQueryService;
    }

    /**
     * POST  /type-racines : Create a new typeRacine.
     *
     * @param typeRacine the typeRacine to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeRacine, or with status 400 (Bad Request) if the typeRacine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-racines")
    @Timed
    public ResponseEntity<TypeRacine> createTypeRacine(@RequestBody TypeRacine typeRacine) throws URISyntaxException {
        log.debug("REST request to save TypeRacine : {}", typeRacine);
        if (typeRacine.getId() != null) {
            throw new BadRequestAlertException("A new typeRacine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeRacine result = typeRacineService.save(typeRacine);
        return ResponseEntity.created(new URI("/api/type-racines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-racines : Updates an existing typeRacine.
     *
     * @param typeRacine the typeRacine to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeRacine,
     * or with status 400 (Bad Request) if the typeRacine is not valid,
     * or with status 500 (Internal Server Error) if the typeRacine couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-racines")
    @Timed
    public ResponseEntity<TypeRacine> updateTypeRacine(@RequestBody TypeRacine typeRacine) throws URISyntaxException {
        log.debug("REST request to update TypeRacine : {}", typeRacine);
        if (typeRacine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeRacine result = typeRacineService.save(typeRacine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeRacine.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-racines : get all the typeRacines.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of typeRacines in body
     */
    @GetMapping("/type-racines")
    @Timed
    public ResponseEntity<List<TypeRacine>> getAllTypeRacines(TypeRacineCriteria criteria) {
        log.debug("REST request to get TypeRacines by criteria: {}", criteria);
        List<TypeRacine> entityList = typeRacineQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /type-racines/count : count all the typeRacines.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/type-racines/count")
    @Timed
    public ResponseEntity<Long> countTypeRacines(TypeRacineCriteria criteria) {
        log.debug("REST request to count TypeRacines by criteria: {}", criteria);
        return ResponseEntity.ok().body(typeRacineQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /type-racines/:id : get the "id" typeRacine.
     *
     * @param id the id of the typeRacine to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeRacine, or with status 404 (Not Found)
     */
    @GetMapping("/type-racines/{id}")
    @Timed
    public ResponseEntity<TypeRacine> getTypeRacine(@PathVariable Long id) {
        log.debug("REST request to get TypeRacine : {}", id);
        Optional<TypeRacine> typeRacine = typeRacineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeRacine);
    }

    /**
     * DELETE  /type-racines/:id : delete the "id" typeRacine.
     *
     * @param id the id of the typeRacine to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-racines/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeRacine(@PathVariable Long id) {
        log.debug("REST request to delete TypeRacine : {}", id);
        typeRacineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
