package com.olympp.backend.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.olympp.backend.domain.TypeRacine;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.TypeRacineRepository;
import com.olympp.backend.service.dto.TypeRacineCriteria;

/**
 * Service for executing complex queries for TypeRacine entities in the database.
 * The main input is a {@link TypeRacineCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TypeRacine} or a {@link Page} of {@link TypeRacine} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TypeRacineQueryService extends QueryService<TypeRacine> {

    private final Logger log = LoggerFactory.getLogger(TypeRacineQueryService.class);

    private final TypeRacineRepository typeRacineRepository;

    public TypeRacineQueryService(TypeRacineRepository typeRacineRepository) {
        this.typeRacineRepository = typeRacineRepository;
    }

    /**
     * Return a {@link List} of {@link TypeRacine} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TypeRacine> findByCriteria(TypeRacineCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TypeRacine> specification = createSpecification(criteria);
        return typeRacineRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link TypeRacine} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeRacine> findByCriteria(TypeRacineCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TypeRacine> specification = createSpecification(criteria);
        return typeRacineRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TypeRacineCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TypeRacine> specification = createSpecification(criteria);
        return typeRacineRepository.count(specification);
    }

    /**
     * Function to convert TypeRacineCriteria to a {@link Specification}
     */
    private Specification<TypeRacine> createSpecification(TypeRacineCriteria criteria) {
        Specification<TypeRacine> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TypeRacine_.id));
            }
            if (criteria.getTypeRacine() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeRacine(), TypeRacine_.typeRacine));
            }
        }
        return specification;
    }
}
