package com.portfolio.myportfolioappback.repository;

import com.portfolio.myportfolioappback.domain.DevEnv;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DevEnvRepository extends JpaRepository<DevEnv, Long> {
    Optional<DevEnv> findAllById(Long id);
}
