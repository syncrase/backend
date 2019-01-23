package com.olympp.backend.repository;

import com.olympp.backend.domain.TypeFeuillage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeFeuillage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeFeuillageRepository extends JpaRepository<TypeFeuillage, Long>, JpaSpecificationExecutor<TypeFeuillage> {

}
