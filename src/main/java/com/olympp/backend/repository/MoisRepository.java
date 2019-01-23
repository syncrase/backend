package com.olympp.backend.repository;

import com.olympp.backend.domain.Mois;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Mois entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MoisRepository extends JpaRepository<Mois, Long>, JpaSpecificationExecutor<Mois> {

}
