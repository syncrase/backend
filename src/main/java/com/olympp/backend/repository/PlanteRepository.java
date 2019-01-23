package com.olympp.backend.repository;

import com.olympp.backend.domain.Plante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Plante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanteRepository extends JpaRepository<Plante, Long>, JpaSpecificationExecutor<Plante> {

    @Query(value = "select distinct plante from Plante plante left join fetch plante.plantCommonNames",
        countQuery = "select count(distinct plante) from Plante plante")
    Page<Plante> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct plante from Plante plante left join fetch plante.plantCommonNames")
    List<Plante> findAllWithEagerRelationships();

    @Query("select plante from Plante plante left join fetch plante.plantCommonNames where plante.id =:id")
    Optional<Plante> findOneWithEagerRelationships(@Param("id") Long id);

}
