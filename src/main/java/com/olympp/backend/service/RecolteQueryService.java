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

import com.olympp.backend.domain.Recolte;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.RecolteRepository;
import com.olympp.backend.service.dto.RecolteCriteria;

/**
 * Service for executing complex queries for Recolte entities in the database.
 * The main input is a {@link RecolteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Recolte} or a {@link Page} of {@link Recolte} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RecolteQueryService extends QueryService<Recolte> {

    private final Logger log = LoggerFactory.getLogger(RecolteQueryService.class);

    private final RecolteRepository recolteRepository;

    public RecolteQueryService(RecolteRepository recolteRepository) {
        this.recolteRepository = recolteRepository;
    }

    /**
     * Return a {@link List} of {@link Recolte} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Recolte> findByCriteria(RecolteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Recolte> specification = createSpecification(criteria);
        return recolteRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Recolte} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Recolte> findByCriteria(RecolteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Recolte> specification = createSpecification(criteria);
        return recolteRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RecolteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Recolte> specification = createSpecification(criteria);
        return recolteRepository.count(specification);
    }

    /**
     * Function to convert RecolteCriteria to a {@link Specification}
     */
    private Specification<Recolte> createSpecification(RecolteCriteria criteria) {
        Specification<Recolte> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Recolte_.id));
            }
            if (criteria.getPlanteId() != null) {
                specification = specification.and(buildSpecification(criteria.getPlanteId(),
                    root -> root.join(Recolte_.plante, JoinType.LEFT).get(Plante_.id)));
            }
            if (criteria.getMoisId() != null) {
                specification = specification.and(buildSpecification(criteria.getMoisId(),
                    root -> root.join(Recolte_.mois, JoinType.LEFT).get(Mois_.id)));
            }
        }
        return specification;
    }
}