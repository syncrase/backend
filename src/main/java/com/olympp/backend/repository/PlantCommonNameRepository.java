package com.olympp.backend.repository;

import com.olympp.backend.domain.PlantCommonName;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlantCommonName entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantCommonNameRepository extends JpaRepository<PlantCommonName, Long>, JpaSpecificationExecutor<PlantCommonName> {

}
