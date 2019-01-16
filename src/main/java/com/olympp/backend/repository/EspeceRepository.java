package com.olympp.backend.repository;

import com.olympp.backend.domain.Espece;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Espece entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EspeceRepository extends JpaRepository<Espece, Long> {

	Optional<Espece> findByName(String name);

}
