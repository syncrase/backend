package com.olympp.backend.repository;

import com.olympp.backend.domain.TypeTerre;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeTerre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeTerreRepository extends JpaRepository<TypeTerre, Long> {

	Optional<TypeTerre> findByTypeTerre(String typeTerre);

}
