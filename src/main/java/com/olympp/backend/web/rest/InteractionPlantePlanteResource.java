package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.InteractionPlantePlante;
import com.olympp.backend.service.InteractionPlantePlanteService;
import com.olympp.backend.web.rest.errors.BadRequestAlertException;
import com.olympp.backend.web.rest.util.HeaderUtil;
import com.olympp.backend.service.dto.InteractionPlantePlanteCriteria;
import com.olympp.backend.service.InteractionPlantePlanteQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing InteractionPlantePlante.
 */
@RestController
@RequestMapping("/api")
public class InteractionPlantePlanteResource {

    private final Logger log = LoggerFactory.getLogger(InteractionPlantePlanteResource.class);

    private static final String ENTITY_NAME = "backendInteractionPlantePlante";

    private final InteractionPlantePlanteService interactionPlantePlanteService;

    private final InteractionPlantePlanteQueryService interactionPlantePlanteQueryService;

    public InteractionPlantePlanteResource(InteractionPlantePlanteService interactionPlantePlanteService, InteractionPlantePlanteQueryService interactionPlantePlanteQueryService) {
        this.interactionPlantePlanteService = interactionPlantePlanteService;
        this.interactionPlantePlanteQueryService = interactionPlantePlanteQueryService;
    }

    /**
     * POST  /interaction-plante-plantes : Create a new interactionPlantePlante.
     *
     * @param interactionPlantePlante the interactionPlantePlante to create
     * @return the ResponseEntity with status 201 (Created) and with body the new interactionPlantePlante, or with status 400 (Bad Request) if the interactionPlantePlante has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/interaction-plante-plantes")
    @Timed
    public ResponseEntity<InteractionPlantePlante> createInteractionPlantePlante(@Valid @RequestBody InteractionPlantePlante interactionPlantePlante) throws URISyntaxException {
        log.debug("REST request to save InteractionPlantePlante : {}", interactionPlantePlante);
        if (interactionPlantePlante.getId() != null) {
            throw new BadRequestAlertException("A new interactionPlantePlante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InteractionPlantePlante result = interactionPlantePlanteService.save(interactionPlantePlante);
        return ResponseEntity.created(new URI("/api/interaction-plante-plantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /interaction-plante-plantes : Updates an existing interactionPlantePlante.
     *
     * @param interactionPlantePlante the interactionPlantePlante to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated interactionPlantePlante,
     * or with status 400 (Bad Request) if the interactionPlantePlante is not valid,
     * or with status 500 (Internal Server Error) if the interactionPlantePlante couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/interaction-plante-plantes")
    @Timed
    public ResponseEntity<InteractionPlantePlante> updateInteractionPlantePlante(@Valid @RequestBody InteractionPlantePlante interactionPlantePlante) throws URISyntaxException {
        log.debug("REST request to update InteractionPlantePlante : {}", interactionPlantePlante);
        if (interactionPlantePlante.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InteractionPlantePlante result = interactionPlantePlanteService.save(interactionPlantePlante);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, interactionPlantePlante.getId().toString()))
            .body(result);
    }

    /**
     * GET  /interaction-plante-plantes : get all the interactionPlantePlantes.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of interactionPlantePlantes in body
     */
    @GetMapping("/interaction-plante-plantes")
    @Timed
    public ResponseEntity<List<InteractionPlantePlante>> getAllInteractionPlantePlantes(InteractionPlantePlanteCriteria criteria) {
        log.debug("REST request to get InteractionPlantePlantes by criteria: {}", criteria);
        List<InteractionPlantePlante> entityList = interactionPlantePlanteQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /interaction-plante-plantes/count : count all the interactionPlantePlantes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/interaction-plante-plantes/count")
    @Timed
    public ResponseEntity<Long> countInteractionPlantePlantes(InteractionPlantePlanteCriteria criteria) {
        log.debug("REST request to count InteractionPlantePlantes by criteria: {}", criteria);
        return ResponseEntity.ok().body(interactionPlantePlanteQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /interaction-plante-plantes/:id : get the "id" interactionPlantePlante.
     *
     * @param id the id of the interactionPlantePlante to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the interactionPlantePlante, or with status 404 (Not Found)
     */
    @GetMapping("/interaction-plante-plantes/{id}")
    @Timed
    public ResponseEntity<InteractionPlantePlante> getInteractionPlantePlante(@PathVariable Long id) {
        log.debug("REST request to get InteractionPlantePlante : {}", id);
        Optional<InteractionPlantePlante> interactionPlantePlante = interactionPlantePlanteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(interactionPlantePlante);
    }

    /**
     * DELETE  /interaction-plante-plantes/:id : delete the "id" interactionPlantePlante.
     *
     * @param id the id of the interactionPlantePlante to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/interaction-plante-plantes/{id}")
    @Timed
    public ResponseEntity<Void> deleteInteractionPlantePlante(@PathVariable Long id) {
        log.debug("REST request to delete InteractionPlantePlante : {}", id);
        interactionPlantePlanteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
