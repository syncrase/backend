package com.olympp.backend.service.impl;

import com.olympp.backend.service.PlanteService;
import com.olympp.backend.domain.Plante;
import com.olympp.backend.repository.PlanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Plante.
 */
@Service
@Transactional
public class PlanteServiceImpl implements PlanteService {

    private final Logger log = LoggerFactory.getLogger(PlanteServiceImpl.class);

    private final PlanteRepository planteRepository;

    public PlanteServiceImpl(PlanteRepository planteRepository) {
        this.planteRepository = planteRepository;
    }

    /**
     * Save a plante.
     *
     * @param plante the entity to save
     * @return the persisted entity
     */
    @Override
    public Plante save(Plante plante) {
        log.debug("Request to save Plante : {}", plante);
        return planteRepository.save(plante);
    }

    /**
     * Get all the plantes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Plante> findAll() {
        log.debug("Request to get all Plantes");
        return planteRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the Plante with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Plante> findAllWithEagerRelationships(Pageable pageable) {
        return planteRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one plante by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Plante> findOne(Long id) {
        log.debug("Request to get Plante : {}", id);
        return planteRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the plante by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Plante : {}", id);
        planteRepository.deleteById(id);
    }
}
