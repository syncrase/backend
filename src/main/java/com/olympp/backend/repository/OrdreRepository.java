package com.olympp.backend.repository;

import com.olympp.backend.domain.Ordre;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ordre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdreRepository extends JpaRepository<Ordre, Long> {

}
