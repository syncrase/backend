package com.olympp.backend.repository;

import com.olympp.backend.domain.Reference;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Reference entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {

}
