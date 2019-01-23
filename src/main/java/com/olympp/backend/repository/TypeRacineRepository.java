package com.olympp.backend.repository;

import com.olympp.backend.domain.TypeRacine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeRacine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeRacineRepository extends JpaRepository<TypeRacine, Long>, JpaSpecificationExecutor<TypeRacine> {

}
