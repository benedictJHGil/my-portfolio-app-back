package com.portfolio.myportfolioappback.repository;

import com.portfolio.myportfolioappback.domain.Awards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardsRepository extends JpaRepository<Awards, Long> {
    Awards findAllById(Long id);
}
