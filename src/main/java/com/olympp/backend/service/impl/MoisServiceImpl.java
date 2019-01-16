package com.olympp.backend.service.impl;

import com.olympp.backend.service.MoisService;
import com.olympp.backend.domain.Mois;
import com.olympp.backend.repository.MoisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Mois.
 */
@Service
@Transactional
public class MoisServiceImpl implements MoisService {

    private final Logger log = LoggerFactory.getLogger(MoisServiceImpl.class);

    private final MoisRepository moisRepository;

    public MoisServiceImpl(MoisRepository moisRepository) {
        this.moisRepository = moisRepository;
    }

    /**
     * Save a mois.
     *
     * @param mois the entity to save
     * @return the persisted entity
     */
    @Override
    public Mois save(Mois mois) {
        log.debug("Request to save Mois : {}", mois);
        return moisRepository.save(mois);
    }

    /**
     * Get all the mois.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Mois> findAll() {
        log.debug("Request to get all Mois");
        return moisRepository.findAll();
    }


    /**
     * Get one mois by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Mois> findOne(Long id) {
        log.debug("Request to get Mois : {}", id);
        return moisRepository.findById(id);
    }

    /**
     * Delete the mois by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mois : {}", id);
        moisRepository.deleteById(id);
    }

	@Override
	public Optional<Mois> findOneByMois(String mois) {
        log.debug("Request to get Mois : {}", mois);
        return moisRepository.findByMois(mois);
	}
}
