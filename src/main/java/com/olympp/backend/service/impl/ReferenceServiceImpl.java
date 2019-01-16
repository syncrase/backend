package com.olympp.backend.service.impl;

import com.olympp.backend.service.ReferenceService;
import com.olympp.backend.domain.Reference;
import com.olympp.backend.repository.ReferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Reference.
 */
@Service
@Transactional
public class ReferenceServiceImpl implements ReferenceService {

    private final Logger log = LoggerFactory.getLogger(ReferenceServiceImpl.class);

    private final ReferenceRepository referenceRepository;

    public ReferenceServiceImpl(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    /**
     * Save a reference.
     *
     * @param reference the entity to save
     * @return the persisted entity
     */
    @Override
    public Reference save(Reference reference) {
        log.debug("Request to save Reference : {}", reference);
        return referenceRepository.save(reference);
    }

    /**
     * Get all the references.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Reference> findAll() {
        log.debug("Request to get all References");
        return referenceRepository.findAll();
    }


    /**
     * Get one reference by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Reference> findOne(Long id) {
        log.debug("Request to get Reference : {}", id);
        return referenceRepository.findById(id);
    }

    /**
     * Delete the reference by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reference : {}", id);
        referenceRepository.deleteById(id);
    }
}
