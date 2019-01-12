package com.olympp.backend.repository;

import com.olympp.backend.domain.InteractionPlantePlante;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InteractionPlantePlante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InteractionPlantePlanteRepository extends JpaRepository<InteractionPlantePlante, Long> {

}
