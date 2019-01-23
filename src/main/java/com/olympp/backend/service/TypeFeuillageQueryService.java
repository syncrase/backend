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

import com.olympp.backend.domain.TypeFeuillage;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.TypeFeuillageRepository;
import com.olympp.backend.service.dto.TypeFeuillageCriteria;

/**
 * Service for executing complex queries for TypeFeuillage entities in the database.
 * The main input is a {@link TypeFeuillageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TypeFeuillage} or a {@link Page} of {@link TypeFeuillage} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TypeFeuillageQueryService extends QueryService<TypeFeuillage> {

    private final Logger log = LoggerFactory.getLogger(TypeFeuillageQueryService.class);

    private final TypeFeuillageRepository typeFeuillageRepository;

    public TypeFeuillageQueryService(TypeFeuillageRepository typeFeuillageRepository) {
        this.typeFeuillageRepository = typeFeuillageRepository;
    }

    /**
     * Return a {@link List} of {@link TypeFeuillage} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TypeFeuillage> findByCriteria(TypeFeuillageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TypeFeuillage> specification = createSpecification(criteria);
        return typeFeuillageRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link TypeFeuillage} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeFeuillage> findByCriteria(TypeFeuillageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TypeFeuillage> specification = createSpecification(criteria);
        return typeFeuillageRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TypeFeuillageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TypeFeuillage> specification = createSpecification(criteria);
        return typeFeuillageRepository.count(specification);
    }

    /**
     * Function to convert TypeFeuillageCriteria to a {@link Specification}
     */
    private Specification<TypeFeuillage> createSpecification(TypeFeuillageCriteria criteria) {
        Specification<TypeFeuillage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TypeFeuillage_.id));
            }
            if (criteria.getTypeFeuillage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeFeuillage(), TypeFeuillage_.typeFeuillage));
            }
        }
        return specification;
    }
}
