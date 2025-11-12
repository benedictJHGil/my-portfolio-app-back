package com.portfolio.myportfolioappback.repository;

import com.portfolio.myportfolioappback.domain.Academic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AcademicRepository extends JpaRepository<Academic, Long> {
    Optional<Academic> findAllById(Long id);

}
