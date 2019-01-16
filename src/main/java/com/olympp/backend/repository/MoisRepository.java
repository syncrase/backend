package com.olympp.backend.repository;

import com.olympp.backend.domain.Mois;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Mois entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MoisRepository extends JpaRepository<Mois, Long> {

	Optional<Mois> findByMois(String mois);

}
