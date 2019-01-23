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

import com.olympp.backend.domain.Livre;
import com.olympp.backend.domain.*; // for static metamodels
import com.olympp.backend.repository.LivreRepository;
import com.olympp.backend.service.dto.LivreCriteria;

/**
 * Service for executing complex queries for Livre entities in the database.
 * The main input is a {@link LivreCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Livre} or a {@link Page} of {@link Livre} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LivreQueryService extends QueryService<Livre> {

    private final Logger log = LoggerFactory.getLogger(LivreQueryService.class);

    private final LivreRepository livreRepository;

    public LivreQueryService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    /**
     * Return a {@link List} of {@link Livre} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Livre> findByCriteria(LivreCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Livre> specification = createSpecification(criteria);
        return livreRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Livre} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Livre> findByCriteria(LivreCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Livre> specification = createSpecification(criteria);
        return livreRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LivreCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Livre> specification = createSpecification(criteria);
        return livreRepository.count(specification);
    }

    /**
     * Function to convert LivreCriteria to a {@link Specification}
     */
    private Specification<Livre> createSpecification(LivreCriteria criteria) {
        Specification<Livre> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Livre_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Livre_.description));
            }
            if (criteria.getIsbn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIsbn(), Livre_.isbn));
            }
            if (criteria.getAuteur() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuteur(), Livre_.auteur));
            }
            if (criteria.getPage() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPage(), Livre_.page));
            }
            if (criteria.getReferenceId() != null) {
                specification = specification.and(buildSpecification(criteria.getReferenceId(),
                    root -> root.join(Livre_.reference, JoinType.LEFT).get(Reference_.id)));
            }
        }
        return specification;
    }
}
