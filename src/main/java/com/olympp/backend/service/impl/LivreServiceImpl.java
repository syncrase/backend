package com.olympp.backend.service.impl;

import com.olympp.backend.service.LivreService;
import com.olympp.backend.domain.Livre;
import com.olympp.backend.repository.LivreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Livre.
 */
@Service
@Transactional
public class LivreServiceImpl implements LivreService {

    private final Logger log = LoggerFactory.getLogger(LivreServiceImpl.class);

    private final LivreRepository livreRepository;

    public LivreServiceImpl(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    /**
     * Save a livre.
     *
     * @param livre the entity to save
     * @return the persisted entity
     */
    @Override
    public Livre save(Livre livre) {
        log.debug("Request to save Livre : {}", livre);
        return livreRepository.save(livre);
    }

    /**
     * Get all the livres.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Livre> findAll() {
        log.debug("Request to get all Livres");
        return livreRepository.findAll();
    }



    /**
     *  get all the livres where Reference is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Livre> findAllWhereReferenceIsNull() {
        log.debug("Request to get all livres where Reference is null");
        return StreamSupport
            .stream(livreRepository.findAll().spliterator(), false)
            .filter(livre -> livre.getReference() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one livre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Livre> findOne(Long id) {
        log.debug("Request to get Livre : {}", id);
        return livreRepository.findById(id);
    }

    /**
     * Delete the livre by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Livre : {}", id);
        livreRepository.deleteById(id);
    }
}
