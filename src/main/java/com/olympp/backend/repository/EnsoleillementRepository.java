package com.olympp.backend.repository;

import com.olympp.backend.domain.Ensoleillement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ensoleillement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnsoleillementRepository extends JpaRepository<Ensoleillement, Long> {

}
