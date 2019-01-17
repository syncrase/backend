package com.olympp.backend.service.impl;

import com.olympp.backend.service.VitesseCroissanceService;
import com.olympp.backend.domain.VitesseCroissance;
import com.olympp.backend.repository.VitesseCroissanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing VitesseCroissance.
 */
@Service
@Transactional
public class VitesseCroissanceServiceImpl implements VitesseCroissanceService {

    private final Logger log = LoggerFactory.getLogger(VitesseCroissanceServiceImpl.class);

    private final VitesseCroissanceRepository vitesseCroissanceRepository;

    public VitesseCroissanceServiceImpl(VitesseCroissanceRepository vitesseCroissanceRepository) {
        this.vitesseCroissanceRepository = vitesseCroissanceRepository;
    }

    /**
     * Save a vitesseCroissance.
     *
     * @param vitesseCroissance the entity to save
     * @return the persisted entity
     */
    @Override
    public VitesseCroissance save(VitesseCroissance vitesseCroissance) {
        log.debug("Request to save VitesseCroissance : {}", vitesseCroissance);
        return vitesseCroissanceRepository.save(vitesseCroissance);
    }

    /**
     * Get all the vitesseCroissances.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VitesseCroissance> findAll() {
        log.debug("Request to get all VitesseCroissances");
        return vitesseCroissanceRepository.findAll();
    }


    /**
     * Get one vitesseCroissance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VitesseCroissance> findOne(Long id) {
        log.debug("Request to get VitesseCroissance : {}", id);
        return vitesseCroissanceRepository.findById(id);
    }

    /**
     * Delete the vitesseCroissance by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VitesseCroissance : {}", id);
        vitesseCroissanceRepository.deleteById(id);
    }
}
