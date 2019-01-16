package com.olympp.backend.repository;

import com.olympp.backend.domain.Famille;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Famille entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FamilleRepository extends JpaRepository<Famille, Long> {

	Optional<Famille> findByName(String name);

}
