package com.olympp.backend.service.impl;

import com.olympp.backend.service.TypeRacineService;
import com.olympp.backend.domain.TypeRacine;
import com.olympp.backend.repository.TypeRacineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing TypeRacine.
 */
@Service
@Transactional
public class TypeRacineServiceImpl implements TypeRacineService {

    private final Logger log = LoggerFactory.getLogger(TypeRacineServiceImpl.class);

    private final TypeRacineRepository typeRacineRepository;

    public TypeRacineServiceImpl(TypeRacineRepository typeRacineRepository) {
        this.typeRacineRepository = typeRacineRepository;
    }

    /**
     * Save a typeRacine.
     *
     * @param typeRacine the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeRacine save(TypeRacine typeRacine) {
        log.debug("Request to save TypeRacine : {}", typeRacine);
        return typeRacineRepository.save(typeRacine);
    }

    /**
     * Get all the typeRacines.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeRacine> findAll() {
        log.debug("Request to get all TypeRacines");
        return typeRacineRepository.findAll();
    }


    /**
     * Get one typeRacine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeRacine> findOne(Long id) {
        log.debug("Request to get TypeRacine : {}", id);
        return typeRacineRepository.findById(id);
    }

    /**
     * Delete the typeRacine by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeRacine : {}", id);
        typeRacineRepository.deleteById(id);
    }

	@Override
	public Optional<TypeRacine> findOneByTypeRacine(String typeRacine) {
        log.debug("Request to get TypeRacine : {}", typeRacine);
        return typeRacineRepository.findByTypeRacine(typeRacine);
	}
}
