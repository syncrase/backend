package com.olympp.backend.service.impl;

import com.olympp.backend.service.TypeFeuillageService;
import com.olympp.backend.domain.TypeFeuillage;
import com.olympp.backend.repository.TypeFeuillageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing TypeFeuillage.
 */
@Service
@Transactional
public class TypeFeuillageServiceImpl implements TypeFeuillageService {

    private final Logger log = LoggerFactory.getLogger(TypeFeuillageServiceImpl.class);

    private final TypeFeuillageRepository typeFeuillageRepository;

    public TypeFeuillageServiceImpl(TypeFeuillageRepository typeFeuillageRepository) {
        this.typeFeuillageRepository = typeFeuillageRepository;
    }

    /**
     * Save a typeFeuillage.
     *
     * @param typeFeuillage the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeFeuillage save(TypeFeuillage typeFeuillage) {
        log.debug("Request to save TypeFeuillage : {}", typeFeuillage);
        return typeFeuillageRepository.save(typeFeuillage);
    }

    /**
     * Get all the typeFeuillages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeFeuillage> findAll() {
        log.debug("Request to get all TypeFeuillages");
        return typeFeuillageRepository.findAll();
    }


    /**
     * Get one typeFeuillage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeFeuillage> findOne(Long id) {
        log.debug("Request to get TypeFeuillage : {}", id);
        return typeFeuillageRepository.findById(id);
    }

    /**
     * Delete the typeFeuillage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeFeuillage : {}", id);
        typeFeuillageRepository.deleteById(id);
    }
}
