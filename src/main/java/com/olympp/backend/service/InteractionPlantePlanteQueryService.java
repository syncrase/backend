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

import com.olympp.backend.domain.InteractionPlantePlante;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.InteractionPlantePlanteRepository;
import com.olympp.backend.service.dto.InteractionPlantePlanteCriteria;

/**
 * Service for executing complex queries for InteractionPlantePlante entities in the database.
 * The main input is a {@link InteractionPlantePlanteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InteractionPlantePlante} or a {@link Page} of {@link InteractionPlantePlante} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InteractionPlantePlanteQueryService extends QueryService<InteractionPlantePlante> {

    private final Logger log = LoggerFactory.getLogger(InteractionPlantePlanteQueryService.class);

    private final InteractionPlantePlanteRepository interactionPlantePlanteRepository;

    public InteractionPlantePlanteQueryService(InteractionPlantePlanteRepository interactionPlantePlanteRepository) {
        this.interactionPlantePlanteRepository = interactionPlantePlanteRepository;
    }

    /**
     * Return a {@link List} of {@link InteractionPlantePlante} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InteractionPlantePlante> findByCriteria(InteractionPlantePlanteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<InteractionPlantePlante> specification = createSpecification(criteria);
        return interactionPlantePlanteRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link InteractionPlantePlante} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InteractionPlantePlante> findByCriteria(InteractionPlantePlanteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<InteractionPlantePlante> specification = createSpecification(criteria);
        return interactionPlantePlanteRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InteractionPlantePlanteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<InteractionPlantePlante> specification = createSpecification(criteria);
        return interactionPlantePlanteRepository.count(specification);
    }

    /**
     * Function to convert InteractionPlantePlanteCriteria to a {@link Specification}
     */
    private Specification<InteractionPlantePlante> createSpecification(InteractionPlantePlanteCriteria criteria) {
        Specification<InteractionPlantePlante> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), InteractionPlantePlante_.id));
            }
            if (criteria.getNotation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotation(), InteractionPlantePlante_.notation));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), InteractionPlantePlante_.description));
            }
            if (criteria.getReferenceId() != null) {
                specification = specification.and(buildSpecification(criteria.getReferenceId(),
                    root -> root.join(InteractionPlantePlante_.references, JoinType.LEFT).get(Reference_.id)));
            }
            if (criteria.getDePlanteId() != null) {
                specification = specification.and(buildSpecification(criteria.getDePlanteId(),
                    root -> root.join(InteractionPlantePlante_.dePlante, JoinType.LEFT).get(Plante_.id)));
            }
            if (criteria.getVersPlanteId() != null) {
                specification = specification.and(buildSpecification(criteria.getVersPlanteId(),
                    root -> root.join(InteractionPlantePlante_.versPlante, JoinType.LEFT).get(Plante_.id)));
            }
        }
        return specification;
    }
}