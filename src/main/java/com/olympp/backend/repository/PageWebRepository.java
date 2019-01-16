package com.olympp.backend.repository;

import com.olympp.backend.domain.PageWeb;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PageWeb entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PageWebRepository extends JpaRepository<PageWeb, Long> {

}
