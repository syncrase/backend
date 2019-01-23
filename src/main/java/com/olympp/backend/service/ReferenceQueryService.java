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

import com.olympp.backend.domain.Reference;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.ReferenceRepository;
import com.olympp.backend.service.dto.ReferenceCriteria;

/**
 * Service for executing complex queries for Reference entities in the database.
 * The main input is a {@link ReferenceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Reference} or a {@link Page} of {@link Reference} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ReferenceQueryService extends QueryService<Reference> {

    private final Logger log = LoggerFactory.getLogger(ReferenceQueryService.class);

    private final ReferenceRepository referenceRepository;

    public ReferenceQueryService(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    /**
     * Return a {@link List} of {@link Reference} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Reference> findByCriteria(ReferenceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Reference> specification = createSpecification(criteria);
        return referenceRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Reference} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Reference> findByCriteria(ReferenceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Reference> specification = createSpecification(criteria);
        return referenceRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ReferenceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Reference> specification = createSpecification(criteria);
        return referenceRepository.count(specification);
    }

    /**
     * Function to convert ReferenceCriteria to a {@link Specification}
     */
    private Specification<Reference> createSpecification(ReferenceCriteria criteria) {
        Specification<Reference> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Reference_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Reference_.description));
            }
            if (criteria.getLivreId() != null) {
                specification = specification.and(buildSpecification(criteria.getLivreId(),
                    root -> root.join(Reference_.livre, JoinType.LEFT).get(Livre_.id)));
            }
            if (criteria.getPageWebId() != null) {
                specification = specification.and(buildSpecification(criteria.getPageWebId(),
                    root -> root.join(Reference_.pageWeb, JoinType.LEFT).get(PageWeb_.id)));
            }
            if (criteria.getInteractionPlantePlanteId() != null) {
                specification = specification.and(buildSpecification(criteria.getInteractionPlantePlanteId(),
                    root -> root.join(Reference_.interactionPlantePlante, JoinType.LEFT).get(InteractionPlantePlante_.id)));
            }
        }
        return specification;
    }
}
