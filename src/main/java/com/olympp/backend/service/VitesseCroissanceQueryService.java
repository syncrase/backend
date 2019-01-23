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

import com.olympp.backend.domain.VitesseCroissance;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.VitesseCroissanceRepository;
import com.olympp.backend.service.dto.VitesseCroissanceCriteria;

/**
 * Service for executing complex queries for VitesseCroissance entities in the database.
 * The main input is a {@link VitesseCroissanceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VitesseCroissance} or a {@link Page} of {@link VitesseCroissance} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VitesseCroissanceQueryService extends QueryService<VitesseCroissance> {

    private final Logger log = LoggerFactory.getLogger(VitesseCroissanceQueryService.class);

    private final VitesseCroissanceRepository vitesseCroissanceRepository;

    public VitesseCroissanceQueryService(VitesseCroissanceRepository vitesseCroissanceRepository) {
        this.vitesseCroissanceRepository = vitesseCroissanceRepository;
    }

    /**
     * Return a {@link List} of {@link VitesseCroissance} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VitesseCroissance> findByCriteria(VitesseCroissanceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<VitesseCroissance> specification = createSpecification(criteria);
        return vitesseCroissanceRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link VitesseCroissance} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VitesseCroissance> findByCriteria(VitesseCroissanceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<VitesseCroissance> specification = createSpecification(criteria);
        return vitesseCroissanceRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VitesseCroissanceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<VitesseCroissance> specification = createSpecification(criteria);
        return vitesseCroissanceRepository.count(specification);
    }

    /**
     * Function to convert VitesseCroissanceCriteria to a {@link Specification}
     */
    private Specification<VitesseCroissance> createSpecification(VitesseCroissanceCriteria criteria) {
        Specification<VitesseCroissance> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), VitesseCroissance_.id));
            }
            if (criteria.getVitesseCroissance() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVitesseCroissance(), VitesseCroissance_.vitesseCroissance));
            }
        }
        return specification;
    }
}
