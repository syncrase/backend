package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.TypeTerre;
import com.olympp.backend.service.TypeTerreService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.TypeTerreCriteria;
import com.olympp.backend.service.TypeTerreQueryService;
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
 * REST controller for managing TypeTerre.
 */
@RestController
@RequestMapping("/api")
public class TypeTerreResource {

    private final Logger log = LoggerFactory.getLogger(TypeTerreResource.class);

    private static final String ENTITY_NAME = "backendTypeTerre";

    private final TypeTerreService typeTerreService;

    private final TypeTerreQueryService typeTerreQueryService;

    public TypeTerreResource(TypeTerreService typeTerreService, TypeTerreQueryService typeTerreQueryService) {
        this.typeTerreService = typeTerreService;
        this.typeTerreQueryService = typeTerreQueryService;
    }

    /**
     * POST  /type-terres : Create a new typeTerre.
     *
     * @param typeTerre the typeTerre to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeTerre, or with status 400 (Bad Request) if the typeTerre has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-terres")
    @Timed
    public ResponseEntity<TypeTerre> createTypeTerre(@RequestBody TypeTerre typeTerre) throws URISyntaxException {
        log.debug("REST request to save TypeTerre : {}", typeTerre);
        if (typeTerre.getId() != null) {
            throw new BadRequestAlertException("A new typeTerre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeTerre result = typeTerreService.save(typeTerre);
        return ResponseEntity.created(new URI("/api/type-terres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-terres : Updates an existing typeTerre.
     *
     * @param typeTerre the typeTerre to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeTerre,
     * or with status 400 (Bad Request) if the typeTerre is not valid,
     * or with status 500 (Internal Server Error) if the typeTerre couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-terres")
    @Timed
    public ResponseEntity<TypeTerre> updateTypeTerre(@RequestBody TypeTerre typeTerre) throws URISyntaxException {
        log.debug("REST request to update TypeTerre : {}", typeTerre);
        if (typeTerre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeTerre result = typeTerreService.save(typeTerre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeTerre.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-terres : get all the typeTerres.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of typeTerres in body
     */
    @GetMapping("/type-terres")
    @Timed
    public ResponseEntity<List<TypeTerre>> getAllTypeTerres(TypeTerreCriteria criteria) {
        log.debug("REST request to get TypeTerres by criteria: {}", criteria);
        List<TypeTerre> entityList = typeTerreQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /type-terres/count : count all the typeTerres.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/type-terres/count")
    @Timed
    public ResponseEntity<Long> countTypeTerres(TypeTerreCriteria criteria) {
        log.debug("REST request to count TypeTerres by criteria: {}", criteria);
        return ResponseEntity.ok().body(typeTerreQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /type-terres/:id : get the "id" typeTerre.
     *
     * @param id the id of the typeTerre to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeTerre, or with status 404 (Not Found)
     */
    @GetMapping("/type-terres/{id}")
    @Timed
    public ResponseEntity<TypeTerre> getTypeTerre(@PathVariable Long id) {
        log.debug("REST request to get TypeTerre : {}", id);
        Optional<TypeTerre> typeTerre = typeTerreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeTerre);
    }

    /**
     * DELETE  /type-terres/:id : delete the "id" typeTerre.
     *
     * @param id the id of the typeTerre to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-terres/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypeTerre(@PathVariable Long id) {
        log.debug("REST request to delete TypeTerre : {}", id);
        typeTerreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
