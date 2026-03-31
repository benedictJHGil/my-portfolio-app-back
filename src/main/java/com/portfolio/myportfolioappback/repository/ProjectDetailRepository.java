package com.portfolio.myportfolioappback.repository;

import com.portfolio.myportfolioappback.domain.ProjectDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProjectDetailRepository extends JpaRepository<ProjectDetail, Long> {
    Optional<ProjectDetail> findByProject_Slug(String slug);
}
