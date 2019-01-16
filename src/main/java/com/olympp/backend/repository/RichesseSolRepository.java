package com.olympp.backend.repository;

import com.olympp.backend.domain.RichesseSol;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RichesseSol entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RichesseSolRepository extends JpaRepository<RichesseSol, Long> {

	Optional<RichesseSol> findByRichesseSol(String richesseSol);

}
