package com.olympp.backend.repository;

import com.olympp.backend.domain.ClassificationCronquist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClassificationCronquist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClassificationCronquistRepository extends JpaRepository<ClassificationCronquist, Long> {

}
