package com.olympp.backend.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.olympp.backend.domain.PageWeb;
import com.olympp.backend.service.PageWebService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing PageWeb.
 */
@RestController
@RequestMapping("/api")
public class PageWebResource {

    private final Logger log = LoggerFactory.getLogger(PageWebResource.class);

    private static final String ENTITY_NAME = "backendPageWeb";

    private final PageWebService pageWebService;

    public PageWebResource(PageWebService pageWebService) {
        this.pageWebService = pageWebService;
    }

    /**
     * POST  /page-webs : Create a new pageWeb.
     *
     * @param pageWeb the pageWeb to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pageWeb, or with status 400 (Bad Request) if the pageWeb has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/page-webs")
    @Timed
    public ResponseEntity<PageWeb> createPageWeb(@RequestBody PageWeb pageWeb) throws URISyntaxException {
        log.debug("REST request to save PageWeb : {}", pageWeb);
        if (pageWeb.getId() != null) {
            throw new BadRequestAlertException("A new pageWeb cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PageWeb result = pageWebService.save(pageWeb);
        return ResponseEntity.created(new URI("/api/page-webs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /page-webs : Updates an existing pageWeb.
     *
     * @param pageWeb the pageWeb to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pageWeb,
     * or with status 400 (Bad Request) if the pageWeb is not valid,
     * or with status 500 (Internal Server Error) if the pageWeb couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/page-webs")
    @Timed
    public ResponseEntity<PageWeb> updatePageWeb(@RequestBody PageWeb pageWeb) throws URISyntaxException {
        log.debug("REST request to update PageWeb : {}", pageWeb);
        if (pageWeb.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PageWeb result = pageWebService.save(pageWeb);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pageWeb.getId().toString()))
            .body(result);
    }

    /**
     * GET  /page-webs : get all the pageWebs.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of pageWebs in body
     */
    @GetMapping("/page-webs")
    @Timed
    public List<PageWeb> getAllPageWebs(@RequestParam(required = false) String filter) {
        if ("reference-is-null".equals(filter)) {
            log.debug("REST request to get all PageWebs where reference is null");
            return pageWebService.findAllWhereReferenceIsNull();
        }
        log.debug("REST request to get all PageWebs");
        return pageWebService.findAll();
    }

    /**
     * GET  /page-webs/:id : get the "id" pageWeb.
     *
     * @param id the id of the pageWeb to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pageWeb, or with status 404 (Not Found)
     */
    @GetMapping("/page-webs/{id}")
    @Timed
    public ResponseEntity<PageWeb> getPageWeb(@PathVariable Long id) {
        log.debug("REST request to get PageWeb : {}", id);
        Optional<PageWeb> pageWeb = pageWebService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pageWeb);
    }

    /**
     * DELETE  /page-webs/:id : delete the "id" pageWeb.
     *
     * @param id the id of the pageWeb to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/page-webs/{id}")
    @Timed
    public ResponseEntity<Void> deletePageWeb(@PathVariable Long id) {
        log.debug("REST request to delete PageWeb : {}", id);
        pageWebService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
