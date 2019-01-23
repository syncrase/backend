package com.olympp.backend.repository;

import com.olympp.backend.domain.Floraison;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Floraison entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FloraisonRepository extends JpaRepository<Floraison, Long>, JpaSpecificationExecutor<Floraison> {

}
