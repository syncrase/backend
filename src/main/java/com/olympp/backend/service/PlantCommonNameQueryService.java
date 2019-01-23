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

import com.olympp.backend.domain.PlantCommonName;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.PlantCommonNameRepository;
import com.olympp.backend.service.dto.PlantCommonNameCriteria;

/**
 * Service for executing complex queries for PlantCommonName entities in the database.
 * The main input is a {@link PlantCommonNameCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PlantCommonName} or a {@link Page} of {@link PlantCommonName} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PlantCommonNameQueryService extends QueryService<PlantCommonName> {

    private final Logger log = LoggerFactory.getLogger(PlantCommonNameQueryService.class);

    private final PlantCommonNameRepository plantCommonNameRepository;

    public PlantCommonNameQueryService(PlantCommonNameRepository plantCommonNameRepository) {
        this.plantCommonNameRepository = plantCommonNameRepository;
    }

    /**
     * Return a {@link List} of {@link PlantCommonName} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PlantCommonName> findByCriteria(PlantCommonNameCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PlantCommonName> specification = createSpecification(criteria);
        return plantCommonNameRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link PlantCommonName} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PlantCommonName> findByCriteria(PlantCommonNameCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PlantCommonName> specification = createSpecification(criteria);
        return plantCommonNameRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PlantCommonNameCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PlantCommonName> specification = createSpecification(criteria);
        return plantCommonNameRepository.count(specification);
    }

    /**
     * Function to convert PlantCommonNameCriteria to a {@link Specification}
     */
    private Specification<PlantCommonName> createSpecification(PlantCommonNameCriteria criteria) {
        Specification<PlantCommonName> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PlantCommonName_.id));
            }
            if (criteria.getCommonName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCommonName(), PlantCommonName_.commonName));
            }
            if (criteria.getPlanteId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanteId(),
                    root -> root.join(PlantCommonName_.plantes, JoinType.LEFT).get(Plante_.id)));
            }
        }
        return specification;
    }
}
