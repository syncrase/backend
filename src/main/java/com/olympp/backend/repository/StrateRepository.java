package com.olympp.backend.repository;

import com.olympp.backend.domain.Strate;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Strate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StrateRepository extends JpaRepository<Strate, Long> {

	Optional<Strate> findByStrate(String strate);

}
