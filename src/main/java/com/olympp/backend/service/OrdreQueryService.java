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

import com.olympp.backend.domain.Ordre;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.OrdreRepository;
import com.olympp.backend.service.dto.OrdreCriteria;

/**
 * Service for executing complex queries for Ordre entities in the database.
 * The main input is a {@link OrdreCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Ordre} or a {@link Page} of {@link Ordre} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrdreQueryService extends QueryService<Ordre> {

    private final Logger log = LoggerFactory.getLogger(OrdreQueryService.class);

    private final OrdreRepository ordreRepository;

    public OrdreQueryService(OrdreRepository ordreRepository) {
        this.ordreRepository = ordreRepository;
    }

    /**
     * Return a {@link List} of {@link Ordre} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Ordre> findByCriteria(OrdreCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Ordre> specification = createSpecification(criteria);
        return ordreRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Ordre} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Ordre> findByCriteria(OrdreCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Ordre> specification = createSpecification(criteria);
        return ordreRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrdreCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Ordre> specification = createSpecification(criteria);
        return ordreRepository.count(specification);
    }

    /**
     * Function to convert OrdreCriteria to a {@link Specification}
     */
    private Specification<Ordre> createSpecification(OrdreCriteria criteria) {
        Specification<Ordre> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Ordre_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Ordre_.name));
            }
        }
        return specification;
    }
}
