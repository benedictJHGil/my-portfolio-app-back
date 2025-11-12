package com.portfolio.myportfolioappback.repository;

import com.portfolio.myportfolioappback.domain.About;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AboutRepository extends JpaRepository<About, Long> {
    Optional<About> findAllById(Long id);
    Optional<About> findTopByOrderByIdAsc();
}
