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

import com.olympp.backend.domain.Mois;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.MoisRepository;
import com.olympp.backend.service.dto.MoisCriteria;

/**
 * Service for executing complex queries for Mois entities in the database.
 * The main input is a {@link MoisCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Mois} or a {@link Page} of {@link Mois} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MoisQueryService extends QueryService<Mois> {

    private final Logger log = LoggerFactory.getLogger(MoisQueryService.class);

    private final MoisRepository moisRepository;

    public MoisQueryService(MoisRepository moisRepository) {
        this.moisRepository = moisRepository;
    }

    /**
     * Return a {@link List} of {@link Mois} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Mois> findByCriteria(MoisCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Mois> specification = createSpecification(criteria);
        return moisRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Mois} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Mois> findByCriteria(MoisCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Mois> specification = createSpecification(criteria);
        return moisRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MoisCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Mois> specification = createSpecification(criteria);
        return moisRepository.count(specification);
    }

    /**
     * Function to convert MoisCriteria to a {@link Specification}
     */
    private Specification<Mois> createSpecification(MoisCriteria criteria) {
        Specification<Mois> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Mois_.id));
            }
            if (criteria.getMois() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMois(), Mois_.mois));
            }
            if (criteria.getRecolteId() != null) {
                specification = specification.and(buildSpecification(criteria.getRecolteId(),
                    root -> root.join(Mois_.recoltes, JoinType.LEFT).get(Recolte_.id)));
            }
            if (criteria.getFloraisonId() != null) {
                specification = specification.and(buildSpecification(criteria.getFloraisonId(),
                    root -> root.join(Mois_.floraisons, JoinType.LEFT).get(Floraison_.id)));
            }
        }
        return specification;
    }
}
