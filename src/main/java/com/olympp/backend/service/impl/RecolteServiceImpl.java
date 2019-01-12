package com.olympp.backend.service.impl;

import com.olympp.backend.service.RecolteService;
import com.olympp.backend.domain.Recolte;
import com.olympp.backend.repository.RecolteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Recolte.
 */
@Service
@Transactional
public class RecolteServiceImpl implements RecolteService {

    private final Logger log = LoggerFactory.getLogger(RecolteServiceImpl.class);

    private final RecolteRepository recolteRepository;

    public RecolteServiceImpl(RecolteRepository recolteRepository) {
        this.recolteRepository = recolteRepository;
    }

    /**
     * Save a recolte.
     *
     * @param recolte the entity to save
     * @return the persisted entity
     */
    @Override
    public Recolte save(Recolte recolte) {
        log.debug("Request to save Recolte : {}", recolte);
        return recolteRepository.save(recolte);
    }

    /**
     * Get all the recoltes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Recolte> findAll() {
        log.debug("Request to get all Recoltes");
        return recolteRepository.findAll();
    }


    /**
     * Get one recolte by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Recolte> findOne(Long id) {
        log.debug("Request to get Recolte : {}", id);
        return recolteRepository.findById(id);
    }

    /**
     * Delete the recolte by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Recolte : {}", id);
        recolteRepository.deleteById(id);
    }
}
