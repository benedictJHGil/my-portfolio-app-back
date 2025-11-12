package com.portfolio.myportfolioappback.repository;

import com.portfolio.myportfolioappback.domain.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {
    Optional<Career> findAllById(Long id);
}
