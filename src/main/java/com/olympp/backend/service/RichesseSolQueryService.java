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

import com.olympp.backend.domain.RichesseSol;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.RichesseSolRepository;
import com.olympp.backend.service.dto.RichesseSolCriteria;

/**
 * Service for executing complex queries for RichesseSol entities in the database.
 * The main input is a {@link RichesseSolCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RichesseSol} or a {@link Page} of {@link RichesseSol} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RichesseSolQueryService extends QueryService<RichesseSol> {

    private final Logger log = LoggerFactory.getLogger(RichesseSolQueryService.class);

    private final RichesseSolRepository richesseSolRepository;

    public RichesseSolQueryService(RichesseSolRepository richesseSolRepository) {
        this.richesseSolRepository = richesseSolRepository;
    }

    /**
     * Return a {@link List} of {@link RichesseSol} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RichesseSol> findByCriteria(RichesseSolCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RichesseSol> specification = createSpecification(criteria);
        return richesseSolRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link RichesseSol} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RichesseSol> findByCriteria(RichesseSolCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RichesseSol> specification = createSpecification(criteria);
        return richesseSolRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RichesseSolCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RichesseSol> specification = createSpecification(criteria);
        return richesseSolRepository.count(specification);
    }

    /**
     * Function to convert RichesseSolCriteria to a {@link Specification}
     */
    private Specification<RichesseSol> createSpecification(RichesseSolCriteria criteria) {
        Specification<RichesseSol> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RichesseSol_.id));
            }
            if (criteria.getRichesseSol() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRichesseSol(), RichesseSol_.richesseSol));
            }
        }
        return specification;
    }
}
