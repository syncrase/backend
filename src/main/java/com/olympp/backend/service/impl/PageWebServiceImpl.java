package com.olympp.backend.service.impl;

import com.olympp.backend.service.PageWebService;
import com.olympp.backend.domain.PageWeb;
import com.olympp.backend.repository.PageWebRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing PageWeb.
 */
@Service
@Transactional
public class PageWebServiceImpl implements PageWebService {

    private final Logger log = LoggerFactory.getLogger(PageWebServiceImpl.class);

    private final PageWebRepository pageWebRepository;

    public PageWebServiceImpl(PageWebRepository pageWebRepository) {
        this.pageWebRepository = pageWebRepository;
    }

    /**
     * Save a pageWeb.
     *
     * @param pageWeb the entity to save
     * @return the persisted entity
     */
    @Override
    public PageWeb save(PageWeb pageWeb) {
        log.debug("Request to save PageWeb : {}", pageWeb);
        return pageWebRepository.save(pageWeb);
    }

    /**
     * Get all the pageWebs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PageWeb> findAll() {
        log.debug("Request to get all PageWebs");
        return pageWebRepository.findAll();
    }



    /**
     *  get all the pageWebs where Reference is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<PageWeb> findAllWhereReferenceIsNull() {
        log.debug("Request to get all pageWebs where Reference is null");
        return StreamSupport
            .stream(pageWebRepository.findAll().spliterator(), false)
            .filter(pageWeb -> pageWeb.getReference() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one pageWeb by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PageWeb> findOne(Long id) {
        log.debug("Request to get PageWeb : {}", id);
        return pageWebRepository.findById(id);
    }

    /**
     * Delete the pageWeb by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PageWeb : {}", id);
        pageWebRepository.deleteById(id);
    }
}
